package com.avocent.plugins.generator.web.controller;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.stream.Collectors.toMap;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import groovy.xml.XmlUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URI;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.avocent.plugins.generator.InvalidNmmXmlFileException;
import com.avocent.plugins.generator.PluginGenerationException;
import com.avocent.plugins.generator.ProjectAlreadyExistsException;
import com.avocent.plugins.generator.ProjectCreateException;
import com.avocent.plugins.generator.ProjectFileDownloadException;
import com.avocent.plugins.generator.ProjectHelpFileUploadException;
import com.avocent.plugins.generator.ProjectImageUploadException;
import com.avocent.plugins.generator.ProjectResourceBundleUploadException;
import com.avocent.plugins.generator.ProjectUploadCommonDataFileException;
import com.avocent.plugins.generator.ProjectUploadMibFileException;
import com.avocent.plugins.generator.ProjectUploadNmmXmlFileException;
import com.avocent.plugins.generator.model.MibTree;
import com.avocent.plugins.generator.model.Project;
import com.avocent.plugins.generator.model.ProjectStatus;
import com.avocent.plugins.generator.model.ProjectType;
import com.avocent.plugins.generator.model.common.CustomCodeInformation;
import com.avocent.plugins.generator.model.common.NMMXMLInformation;
import com.avocent.plugins.generator.model.common.OBWIConfiguration;
import com.avocent.plugins.generator.model.common.SNMPConfiguration;
import com.avocent.plugins.generator.model.common.TrapsInformation;
import com.avocent.plugins.generator.model.view.ContainerViewComponent;
import com.avocent.plugins.generator.model.view.ElementViewComponent;
import com.avocent.plugins.generator.web.CustomCodeResource;
import com.avocent.plugins.generator.web.DatapointMappingResource;
import com.avocent.plugins.generator.web.FileResource;
import com.avocent.plugins.generator.web.FirmwareCustomCodeResource;
import com.avocent.plugins.generator.web.NMMMappingResource;
import com.avocent.plugins.generator.web.NmmXmlModelResource;
import com.avocent.plugins.generator.web.OBWIMappingResource;
import com.avocent.plugins.generator.web.PluginArtifactResource;
import com.avocent.plugins.generator.web.ProjectResource;
import com.avocent.plugins.generator.web.SNMPMappingResource;
import com.avocent.plugins.generator.web.TrapsMappingResource;

@RestController
@RequestMapping("/project")
public class ProjectRestController extends BaseRestController{
	
	@RequestMapping(value="/create", method = RequestMethod.POST, 
			consumes={MULTIPART_FORM_DATA_VALUE},
			produces={APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public ProjectResource create(@RequestPart("mibFile") Part mibFile,
					@RequestParam("name") String projectName, 
					@RequestParam("type") ProjectType projectType, UriComponentsBuilder ucb) {
		
		LOG.debug("process create project...");
	
		if(!projectService.validateProjectWithNameNotExists(projectName)){
			throw new ProjectAlreadyExistsException( projectName );
		}	
		
		try(InputStream input = mibFile.getInputStream())
		{
			projectPackagingService.createProjectStructure(projectName);
			MibTree mibTree = projectPackagingService.saveMibFile(projectName, 
					mibFile.getSubmittedFileName(), input, servletContext.getContextPath(),
					true); 
					
			Project project = createProject(mibTree, mibFile.getSubmittedFileName(), 
						projectName, projectType);
			
			projectService.saveProject(project);			
			
			LOG.debug("Populate response to create request");		
			
			ProjectResource projectResource = new ProjectResource(
					project.getName(), project.getType(), project.getStatus(), project.getCreatedDate(),
					project.getLastModified());
			
			URI contextPath = ucb.path("/").build().toUri();	 
			projectResource.setLinks(projectLinks.projectLinks(project.getName(), contextPath.toString()));		
				
			LOG.debug("Successfuly saved project with name ({})", project.getName());
			
			return projectResource;
		}catch(IOException e){
			LOG.error("Error processing MIB file.", e);
			throw new ProjectCreateException(projectName);
		}
	}
	
	@RequestMapping(value="/createFromMapping", method = RequestMethod.POST, 
			consumes={MULTIPART_FORM_DATA_VALUE},
			produces={APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public ProjectResource createFromMapping(@RequestPart("mibFile") Part mibFile,
					@RequestPart("mappingFile") Part mappingFile,
					@RequestParam("name") String projectName, UriComponentsBuilder ucb) {
		
		LOG.debug("Process create project from mapping file..."); 
		
		Path tempFile = null;
		try(InputStream mappings = mappingFile.getInputStream())
		{
			tempFile = projectPackagingService.getTempBaseDirPath().resolve(
					Files.createTempFile("mibMapping", "xml"));
			Files.copy(mappings, tempFile, StandardCopyOption.REPLACE_EXISTING);
			ProjectType type = projectDatapointPopulater.retrieveProjectType(tempFile.toFile());
			// Reuse create() method
			ProjectResource projectResource = create(mibFile, projectName, type, ucb);

			Project project = projectService.getProjectByName(projectName);
			projectDatapointPopulater.populateWithMappings(project, tempFile.toFile());
			
			projectService.saveProject(project);
			
			return projectResource;
		}catch(IOException e){
			LOG.error("Error processing MIB XML mapping file.", e);
			throw new ProjectCreateException(projectName);
		}finally{
			try {
				Files.deleteIfExists(tempFile);
			} catch (IOException IGNORE) {
			}
		}
	}
	
	@RequestMapping(value="/datapoints/groups/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public Map<String, String> datapointsGroupMappings(@PathVariable String projectName){
		
		LOG.debug("Get datapoints label to group mappings for  project with name ({})", projectName);
		
		Project project = projectService.getProjectByName(projectName);
	 			
		return project.getDataPointMapping().stream()
			   .collect(toMap(cvc-> cvc.getLabel(), cvc-> cvc.getName()));
	}
	
	@RequestMapping(value="/delete/{projectName}",
			method={RequestMethod.DELETE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String projectName){
		LOG.debug("Delete project with name ({})", projectName);		
		
		projectService.deleteProject(projectName);
		
		LOG.debug("Successfully delete project ({})", projectName);
	}
	
	@RequestMapping(value="/nmmxml/common/{projectName}/{commonDataFileName}",
			method={RequestMethod.DELETE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCommonDataFile(@PathVariable String projectName, 
			@PathVariable String commonDataFileName){
		
		projectService.verifyProjectExists(projectName);
		
		LOG.debug("Delete common data file with name ({}) for project ({})",
				commonDataFileName, projectName);
		
		Path commonDataDirPath = projectPackagingService.getProjectCommonDataDirPath(projectName);
		Path commonDataFile =  commonDataDirPath.resolve(commonDataFileName + ".xml");
		
		if(Files.exists(commonDataFile)){			
			try{
				Files.delete(commonDataFile);
			}catch(IOException e){
				LOG.error(
						String.format("Failed to delete file (%s)", commonDataFile.getFileName().toString() 
								), e);
			}
		}else{
			LOG.warn("Common data file ({}) not found?", commonDataFile.getFileName().toString());
		}
	}
	
	@RequestMapping(value="/customCode/{projectName}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomCodeJarFile(@PathVariable("projectName") String projectName) {
		
		LOG.debug("Delete the current custom code Jar file for project ({})",
				projectName);
		
		Project project = projectService.getProjectByName(projectName);
		
		projectPackagingService.emptyCustomCodeDirectory(projectName);
		
		project.setCustomCode(null);
		project.setLastModified(new Date());
		projectService.saveProject(project);
		
		LOG.debug("Reset custom code values");
		
	}	
	
	@RequestMapping(value="/nmmxml/{projectName}/{nmmXmlFileName}",
			method={RequestMethod.DELETE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteNmmXmlFile(@PathVariable String projectName, 
			@PathVariable String nmmXmlFileName){
		
		projectService.verifyProjectExists(projectName);
		
		LOG.debug("Delete NMM XML File with name ({}) for project ({})",
				nmmXmlFileName, projectName);
		
		Path nmmXmlDirPath = projectPackagingService.getProjectXmlNmmDirPath(projectName);
		Path nmmXmlFile = nmmXmlDirPath.resolve(nmmXmlFileName + ".xml");
		
		if(Files.exists(nmmXmlFile)){			
			try{
				Files.delete(nmmXmlFile);
			}catch(IOException e){
				LOG.error(
						String.format("Failed to delete file (%s)", nmmXmlFile.getFileName().toString() 
								), e);
			}
		}else{
			LOG.warn("NMM XML file ({}) not found?", nmmXmlFile.getFileName().toString());
		}
	}
	
	@RequestMapping(value="/plugins/{projectName}/{fileName}",
			method={RequestMethod.DELETE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePlugin(@PathVariable String projectName, 
					@PathVariable String fileName){
		LOG.debug("Delete plugin ({}) for project ({})", fileName, projectName);	
		
		Project project = projectService.getProjectByName(projectName);	
		
		Path projectDistPath = projectPackagingService.getProjectPluginsDistDirPath(projectName);
		Path pluginPath = projectDistPath.resolve(fileName + ".jar");
		if(Files.exists(pluginPath)){
			try {
				Files.delete(pluginPath);
			} catch (IOException e)
			{
				LOG.error(
						String.format("Failed to delete file (%s)", fileName + ".jar" 
								), e);
			}
		}else{
			LOG.warn("Plugin file ({}) not found?", fileName + ".jar");
		}
		
		try(Stream<Path> stream = Files.list(projectDistPath)){
			if(stream.count() <= 0){
				LOG.debug("All the plugins were deleted, changing project status to complete!");
				project.setStatus(ProjectStatus.COMPLETE);
				projectService.saveProject(project);
			}
		} catch (IOException e) {
			LOG.error(
					String.format("Failed to iterate directory (%s)", projectDistPath.toString() 
							), e);
		}
	}
	
	
	@RequestMapping(value="/download/{projectName}/{fileName}",
			method={RequestMethod.GET})	
	@ResponseBody
	public void downloadPlugin(@PathVariable String projectName, @PathVariable String fileName,
			HttpServletResponse httpResponse){
		LOG.debug("Download project ({}) artifact jar file ({})", projectName,
				fileName);
		
		projectService.verifyProjectExists(projectName);
		
		Path projectPluginDistDir = projectPackagingService.getProjectPluginsDistDirPath(projectName);
		Path pluginJarFile = projectPluginDistDir.resolve(fileName +  ".jar");
		
		if(!Files.exists(pluginJarFile)){
			throw new ProjectFileDownloadException(projectName, 
					new java.nio.file.NoSuchFileException(fileName));
		}
	
		try(OutputStream os = httpResponse.getOutputStream()){
			LOG.debug("Download file => ({}), size => ({})", fileName+".jar", 
					Files.size(pluginJarFile));		
			httpResponse.setContentType("application/jar");      
			httpResponse.setHeader("Content-Disposition",
					"attachment; filename=" + pluginJarFile.toFile().getName()); 
			Files.copy(pluginJarFile, os);
			os.flush();
		}catch(IOException e){
			LOG.error("Failed to copy ({}) contents", pluginJarFile.toFile().getName());
			throw new ProjectFileDownloadException(projectName, e);
		}		
	}
	
	@RequestMapping(value="/download/{projectName}",
			method={RequestMethod.GET})	
	@ResponseBody
	public void downloadProject(@PathVariable String projectName, 
			HttpServletResponse httpResponse){
		LOG.debug("Download project ({}) artifact zip file", projectName );
		
		Project project =  projectService.getProjectByName(projectName);
		
		projectPackagingService.zipProjectArtifacts(project);
	
		Path distArtifact = projectPackagingService.getProjectDistDirPath(projectName);
		Path zipFile = distArtifact.resolve(project.getName() + ".zip");
		
		try(OutputStream os = httpResponse.getOutputStream()){
			LOG.debug("Download file => {}, size => {}", zipFile.toAbsolutePath(), 
					Files.size(zipFile));		
			httpResponse.setContentType("application/zip");      
			httpResponse.setHeader("Content-Disposition",
					"attachment; filename=" + zipFile.toFile().getName()); 
			Files.copy(zipFile, os);
			os.flush();
		}catch(IOException e){
			LOG.error("Failed to copy ({}) contents", zipFile.toFile().getName());
			throw new ProjectFileDownloadException(projectName, e);
		}		 
	}
	
	@RequestMapping(value="/generate/{projectName}",
			method={RequestMethod.PUT})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void generate( @PathVariable String projectName ){
		
		LOG.debug("Generate custom jar plugin for project ({})", projectName);
		
		Project project = projectService.getProjectByName(projectName);
		try 
		{			
			projectPackagingService.packageCustomPlugin(project);
			project.setStatus(ProjectStatus.GENERATED);
			project.setLastModified(new Date());
			projectService.saveProject(project);
			LOG.debug("Successfully generated artifacts for project ({})", 
					projectName);
		} catch (Exception e) {	
			 LOG.error("Failed to generate project artifacts", 
					 null != e.getCause()?e.getCause(): e);
			 throw new PluginGenerationException(projectName, 
					 null != e.getCause()?e.getCause(): e);
		}
	}
	
	@RequestMapping(value="/nmmxml/exist/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public Boolean nmmXmlFileExistForProjectName(
			@PathVariable String projectName){
		
		projectService.verifyProjectExists(projectName);
		
		Path nmmXmlBaseDir = 
				projectPackagingService.getProjectXmlNmmDirPath(projectName);
		
		Boolean result = Boolean.FALSE;		
		try(Stream<Path> stream = Files.list(nmmXmlBaseDir).filter(path->
						path.toFile().getName().endsWith(".xml"))){ 
			if(stream.count() > 0){
				result = Boolean.TRUE;
			}
		} catch (IOException e) {
			LOG.error(
					String.format("Failed to iterate directory (%s)", nmmXmlBaseDir.toString() 
							), e);
		}
		
		return result;
	}
		
	@RequestMapping(value="/open/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public ProjectResource open(@PathVariable String projectName, 
				UriComponentsBuilder ucb){
		
		LOG.debug("Open partial project with name ({})", projectName);
		
		Project project = projectService.getProjectByName(projectName);
		 
		ProjectResource projectResource = new ProjectResource(
				projectName, project.getType(), project.getStatus(),
				project.getCreatedDate(), project.getLastModified());
	 
		URI contextPath = ucb.path("/").build().toUri();
		projectResource.setLinks(projectLinks.projectLinks(project.getName(), contextPath.toString()));			
		
		return projectResource;
	}
	
	
	/**
	 * <p>
	 * Preview the XML mappings generated for a given project. 
	 * </p>
	 * 
	 * @param projectName The project name to preview the XML
	 * 
	 * @return XML as a string
	 */
	@RequestMapping(value="/preview/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_XML_VALUE})	
	public ResponseEntity<String> preview(@PathVariable String projectName){		
		LOG.debug("Preview generated XML file for project ({0})", projectName);
		Project project = projectService.getProjectByName(projectName);		 
		try {
			String xml = projectService.previewProject(project);		
			return new ResponseEntity<String>(XmlUtil.serialize(xml), HttpStatus.OK); 
		} catch (IOException e) {
			LOG.error("Failed to read generated XML file", e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@RequestMapping(value="/bundles/exist/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public Boolean resourceBundlesExistForProjectName(
			@PathVariable String projectName){
		
		projectService.verifyProjectExists(projectName);
		
		Path resourceBundleBaseDir = 
				projectPackagingService.getProjectResourceBundleBaseDirPath(projectName);
		
		Boolean result = Boolean.FALSE;		
		try(Stream<Path> stream = Files.list(resourceBundleBaseDir)){
			if(stream.count() > 0){
				 result = Boolean.TRUE;
			}
		} catch (IOException e) {
			LOG.error(
					String.format("Failed to iterate directory (%s)", resourceBundleBaseDir.toString() 
							), e);
		}
		
		return result;
	}
	
	@RequestMapping(method={RequestMethod.GET}, produces={APPLICATION_JSON_VALUE})
	public List<ProjectResource> retrieveAll(UriComponentsBuilder ucb){
		
		LOG.debug("Retrieve all projects");
		final URI contextPath = ucb.path("/").build().toUri();		
		
		return projectService.getAllProjects()
		                 .stream()
		                 .map(project->{
		                	 ProjectResource pr = new ProjectResource(project.getName(),
		                		 project.getType(), project.getStatus(), project.getCreatedDate(),
		                		 project.getLastModified()); 
		                	 pr.addLink("openProject", contextPath, "project", "open", project.getName());
		                	 if(ProjectStatus.COMPLETE == project.getStatus()){
		                		 pr.addLink("generateProject", contextPath, "project", "generate",
		                				 project.getName())
		                		   .addLink("previewProject", contextPath, "project", "preview",
		                				 project.getName());
		                	 }else if(ProjectStatus.GENERATED == project.getStatus()){
		                		 pr.addLink("downloadProject", contextPath, "project", "download",
		                				 project.getName());
		                		 pr.addLink("projectArtifacts", contextPath, "project", "plugins",
		                				 project.getName());
		                		 pr.addLink("deleteArtifact", contextPath, "project", "plugins",
		                				 project.getName());
		                	 }
		                	 return pr;
		                 })
		                 .collect(Collectors.toList());
	}
	
	@RequestMapping(value="/status/{status}",
			method={RequestMethod.GET}, produces={APPLICATION_JSON_VALUE})
	public List<ProjectResource> retrieveAllByStatus(
			@PathVariable ProjectStatus status, UriComponentsBuilder ucb){
		
		LOG.debug("Retrieve all projects by status => {}", status);
		final URI contextPath = ucb.path("/").build().toUri();	
		
		return projectService.getAllProjectsByStatus(status)
				 .stream()
				 .map(project->{
                	 ProjectResource pr = new ProjectResource(project.getName(),
                		 project.getType(), project.getStatus(), project.getCreatedDate(),
                		 project.getLastModified()); 
                	 pr.addLink("openProject", contextPath, "project", "open", project.getName());
                	 if(ProjectStatus.COMPLETE == project.getStatus()){
                		 pr.addLink("generateProject", contextPath, "project", "generate",
                				 project.getName())
                		   .addLink("previewProject", contextPath, "project", "preview",
                				 project.getName())
                		   .addLink("seedPluginInstalled", contextPath, "config", "installed");
                	 }else if(ProjectStatus.GENERATED == project.getStatus()){
                		 pr.addLink("downloadProject", contextPath, "project", "download",
                				 project.getName());
                		 pr.addLink("projectArtifacts", contextPath, "project", "plugins",
                				 project.getName());
                		 pr.addLink("deleteArtifact", contextPath, "project", "plugins",
                				 project.getName());
                	 }                	 
                	 return pr;
                 })
                 .collect(Collectors.toList());
	}
	
	@RequestMapping(value="/type/{type}",
			method={RequestMethod.GET}, produces={APPLICATION_JSON_VALUE})
	public List<ProjectResource> retrieveAllByType(
			@PathVariable ProjectType type, UriComponentsBuilder ucb){
		
		LOG.debug("Retrieve all projects by type => {}", type);
		final URI contextPath = ucb.path("/").build().toUri();	
		
		return projectService.getAllProjectsByType(type)
				 .stream()
				 .map(project->{
               	 ProjectResource pr = new ProjectResource(project.getName(),
               		 project.getType(), project.getStatus(), project.getCreatedDate(),
               		 project.getLastModified()); 
               	 pr.addLink("openProject", contextPath, "project", "open", project.getName());
               	 if(ProjectStatus.COMPLETE == project.getStatus()){
           		     pr.addLink("generateProject", contextPath, "project", "generate",
           				 project.getName())
           			   .addLink("previewProject", contextPath, "project", "preview",
           				 project.getName())
           			   .addLink("seedPluginInstalled", contextPath, "config", "installed")	 ;
               	 }else if(ProjectStatus.GENERATED == project.getStatus()){
            		 pr.addLink("downloadProject", contextPath, "project", "download",
            				 project.getName());
            		 pr.addLink("projectArtifacts", contextPath, "project", "plugins",
            				 project.getName());
            		 pr.addLink("deleteArtifact", contextPath, "project", "plugins",
            				 project.getName());
            	 }
               	 return pr;
                })
                .collect(Collectors.toList());
	}
	
	@RequestMapping(value="/images/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public List<String> retrieveAllImagesByProjectName(@PathVariable String projectName){
		LOG.debug("Retrieve all image file names associated with project ({})", projectName);
		
		projectService.verifyProjectExists(projectName);
		
		Path imageDir = 
				projectPackagingService.getProjectImageBaseDirPath(projectName);
		
		try(Stream<Path> images = Files.walk(imageDir)){
			return images.filter(path-> Files.isRegularFile(path))
						 .map(path-> path.toFile().getName())
						 .collect(Collectors.toList());
			
		}catch(IOException e){
			LOG.error("Failed to retrieve list of image files", e);
			throw new DirectoryIteratorException(e);
		}
	}
	
	@RequestMapping(value="/nmmxml/common/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public List<FileResource> retrieveCommonData(@PathVariable String projectName){
		
		try(Stream<Path> commonFilesStream = Files.list(
				projectPackagingService.getProjectCommonDataDirPath(projectName))){
			
			return commonFilesStream
					.filter(path-> Files.isRegularFile(path) && 
							path.getFileName().toString().endsWith(".xml"))
					.map(path-> {
						String fileName = path.getFileName().toString();
						FileResource resource = new FileResource( 
								fileName.substring(0, fileName.lastIndexOf(".")),
								new Date(path.toFile().lastModified()));
				 
						return resource;
					})
					.collect(Collectors.toList());
		}catch(IOException e){
			LOG.error("Failed to iterate directory ({})",
					projectPackagingService.getProjectCommonDataDirPath(projectName));
			throw new DirectoryIteratorException(e);
		}
	}
	
	@RequestMapping(value="/customCode/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public CustomCodeResource retrieveCustomCode(@PathVariable String projectName, 
				UriComponentsBuilder ucb){
		LOG.debug("Retrieve custom code mapping for project with name ({})", projectName);
		
		Project project = projectService.getProjectByName(projectName);
		
		CustomCodeResource customCode = new CustomCodeResource(
			project.getName(), project.getType(), project.getStatus(),
			project.getCustomCode());
				
		
		URI contextPath = ucb.path("/").build().toUri(); 
		customCode 
		.addLink("saveCustomCode", contextPath, "project", "save", "customCode", project.getName())
		.addLink("validateCustomCode", contextPath, "project", "validate", "customCode", project.getName())
		.addLink("deleteProject", contextPath, "project", "delete", project.getName());	
		
		return customCode;
	}
	
	
	@RequestMapping(value="/datapoints/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public DatapointMappingResource retrieveDatapoints(@PathVariable String projectName, 
				UriComponentsBuilder ucb){
		LOG.debug("Retrieve datapoint mapping for project ({})", projectName);
		
		Project project = projectService.getProjectByName(projectName);
		
		DatapointMappingResource datapoints = new DatapointMappingResource(
				project.getName(), project.getType(), project.getStatus(), 
				project.getDataPointMapping());
		
		URI contextPath = ucb.path("/").build().toUri(); 
		datapoints 
		.addLink("mibTree", contextPath, "mib", project.getName())
		.addLink("saveDatapoints", contextPath, "project", "save", "traps", project.getName())
		.addLink("validateDatapoints", contextPath, "project", "validate", "traps", project.getName())
		.addLink("mibDetail", contextPath, "mig", "detail", project.getName())
		.addLink("deleteProject", contextPath, "project", "delete", project.getName());	
		
		return datapoints;
	}
	
	@RequestMapping(value="/image/{projectName}",
			method={RequestMethod.GET}, produces={APPLICATION_OCTET_STREAM_VALUE})
	public byte[] retrieveImageAsBinaryByProjectName(@PathVariable String projectName, 
			@RequestParam("fileName") String imageName){
				
		LOG.debug("For project ({}) retrieve image ({}) as binary file",
				projectName, imageName);	
		
		projectService.verifyProjectExists(projectName);
	
		Path path = projectPackagingService.getProjectImageFilePath(projectName, imageName); 
		try {
			return Files.readAllBytes(path); 
		} catch (IOException e) {
			LOG.error("Failed to download image file with name ({})", imageName);
			return new byte[0];
		}
	}
	
	
	@RequestMapping(value="/nmm/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public NMMMappingResource retrieveNmmMappings(@PathVariable String projectName, 
				UriComponentsBuilder ucb){
		LOG.debug("Retrieve NMM mapping for project with name ({})", projectName);
		
		Project project = projectService.getProjectByName(projectName);
		
		NMMMappingResource nmm = new NMMMappingResource(
				project.getName(), project.getType(), project.getStatus(), 
				project.getNmmMapping());
		
		URI contextPath = ucb.path("/").build().toUri(); 
		nmm 
		.addLink("saveNMM", contextPath, "project", "save", "nmm", project.getName())
		.addLink("validateNMM", contextPath, "project", "validate", "nmm", project.getName())
		.addLink("deleteProject", contextPath, "project", "delete", project.getName());	
		
		return nmm;
	}
	

	@RequestMapping(value="/nmmxml/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public List<NmmXmlModelResource> retrieveNmmXmlModels(@PathVariable String projectName){

		try(Stream<Path> nmmFilesStream = Files.list( 
					projectPackagingService.getProjectXmlNmmDirPath(projectName))){			
			return nmmFilesStream
					.filter(path-> Files.isRegularFile(path) 
							&& path.getFileName().toString().endsWith(".xml"))
					.map(path-> {
						String fileName = path.getFileName().toString();
						NmmXmlModelResource resource = new NmmXmlModelResource(
								fileName.substring(0, fileName.lastIndexOf(".")),
									projectPackagingService.retrieveNmmApiType(
											projectName, path.toFile()), 
									new Date(path.toFile().lastModified()));
				 
						return resource;
					})
					.collect(Collectors.toList());
		}catch(IOException e){
			LOG.error("Failed to iterate directory ({})",
					projectPackagingService.getProjectXmlNmmDirPath(projectName));
			throw new DirectoryIteratorException(e);
		}
	}
	
	@RequestMapping(value="/obwi/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public OBWIMappingResource retrieveOBWI(@PathVariable String projectName, 
				UriComponentsBuilder ucb){
		LOG.debug("Retrieve OBWI mapping for project with name ({})", projectName);
		
		Project project = projectService.getProjectByName(projectName);
		
		OBWIMappingResource traps = new OBWIMappingResource(
				project.getName(), project.getType(), project.getStatus(), 
				project.getObwiMapping());
		
		URI contextPath = ucb.path("/").build().toUri(); 
		traps 
		.addLink("saveOBWI", contextPath, "project", "save", "obwi", project.getName())
		.addLink("validateOBWI", contextPath, "project", "validate", "obwi", project.getName())
		.addLink("deleteProject", contextPath, "project", "delete", project.getName());	
		
		return traps;
	}

	
	@RequestMapping(value="/plugins/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public List<PluginArtifactResource> retrievePlugins(@PathVariable String projectName){
		
		LOG.debug("Retrieve a list of generated plugins for project with name ({})", projectName);
		
		projectService.verifyProjectExists(projectName);
		
		Path distDir = projectPackagingService.getProjectPluginsDistDirPath(projectName);
		LOG.debug("Using the following folder ({}) as base for all generated plugins", 
				distDir.toString());
		 
		try(Stream<Path> pluginPaths = Files.walk(distDir))
		{
			return pluginPaths
					   .filter(path-> Files.isRegularFile(path))
					   .map(path-> {
						   List<String> parts = projectPackagingService.splitCustomPluginFileName(path.toFile());					 
						   return new PluginArtifactResource(parts.get(0), parts.get(1),
								   new Date(path.toFile().lastModified()));
					   })
					   .sorted()
					   .collect(Collectors.toList());
		}catch(IOException e){
			LOG.error("Failed to retrieve list of all generated custom plugins", e);
			throw new DirectoryIteratorException(e);
		}
	}
	
	@RequestMapping(value="/bundles/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public Map<String, String> retrieveResourceBundlesByProjectName(
			@PathVariable String projectName){
		
		projectService.verifyProjectExists(projectName);
			
		final Locale clientLocale = LocaleContextHolder.getLocale();
		LOG.debug("Client's locale =>  {}", clientLocale);
		
		Path resourceBundleBaseDir = 
				projectPackagingService.getProjectResourceBundleBaseDirPath(projectName);
		
		final ClassLoader loader = projectService.getProjectResourceBundleClassloader(
				projectName, resourceBundleBaseDir);
		
		Map<String, String> result = new TreeMap<>();
		try(Stream<Path> resourceBundles = Files.walk(resourceBundleBaseDir))
		{
				resourceBundles
				.filter(path-> Files.isRegularFile(path))
				.forEach(path-> {
					ResourceBundle bundle =
							ResourceBundle.getBundle(
						projectPackagingService.resolveResourceBundleBaseName(path), 
						clientLocale, loader);
					if(null == bundle) return;
					for(final String key: bundle.keySet()){
						result.put(key, bundle.getString(key));
					}				 
				});
			
			return result;
		}catch(IOException e){
			LOG.error("Failed to retrieve list of resoruce bundles", e);
			throw new DirectoryIteratorException(e);
		}
	}
	
	
	@RequestMapping(value="/snmp/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public SNMPMappingResource retrieveSNMP(@PathVariable String projectName, 
				UriComponentsBuilder ucb){
		LOG.debug("Retrieve SNMP mapping for project with name ({})", projectName);
		
		Project project = projectService.getProjectByName(projectName);
		
		SNMPMappingResource snmp = new SNMPMappingResource(
				project.getName(), project.getType(), project.getStatus(), 
				project.getSnmpMapping());
		
		URI contextPath = ucb.path("/").build().toUri(); 
		snmp 
		.addLink("saveSNMP", contextPath, "project", "save", "snmp", project.getName())
		.addLink("validateSNMP", contextPath, "project", "validate", "snmp", project.getName())
		.addLink("deleteProject", contextPath, "project", "delete", project.getName());	
		
		return snmp;
	}
	
	@RequestMapping(value="/traps/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public TrapsMappingResource retrieveTraps(@PathVariable String projectName, 
				UriComponentsBuilder ucb){
		LOG.debug("Retrieve traps mapping for project with name ({})", projectName);
		
		Project project =  projectService.getProjectByName(projectName);
		
		TrapsMappingResource traps = new TrapsMappingResource(
				project.getName(), project.getType(), project.getStatus(), 
				project.getTrapsMapping());
		
		URI contextPath = ucb.path("/").build().toUri(); 
		traps 
		.addLink("mibTree", contextPath, "mib", project.getName())
		.addLink("saveTraps", contextPath, "project", "save", "traps", project.getName())
		.addLink("validateTraps", contextPath, "project", "validate", "traps", project.getName())
		.addLink("deleteProject", contextPath, "project", "delete", project.getName());	
		
		return traps;
	}
	
	
	@RequestMapping(value="/firmware/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public List<FirmwareCustomCodeResource> retrieveUploadedFirmware(@PathVariable String projectName){
		
		LOG.debug("Retrieve firmware file info for project with name ({})", projectName);
		
		projectService.verifyProjectExists(projectName);
		
		Path distDir = projectPackagingService.getProjectCustomCodeDirPath(projectName);
		LOG.debug("Using the following folder ({}) as base for firmware custom code", 
				distDir.toString());
		 
		try(Stream<Path> pluginPaths = Files.walk(distDir))
		{
			return pluginPaths
					   .filter(path-> Files.isRegularFile(path))
					   .map(path-> {						 					 
						   return new FirmwareCustomCodeResource(path.getFileName().toString(),
								   new Date(path.toFile().lastModified()));
					   })
					   .collect(Collectors.toList());
		}catch(IOException e){
			LOG.error("Failed to retrieve list of all generated custom plugins", e);
			throw new DirectoryIteratorException(e);
		}
	}
	
	@RequestMapping(value="/save/customCode/{projectName}",
			method={RequestMethod.PUT},  consumes={APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void save(@PathVariable String projectName, 
			@RequestBody CustomCodeInformation customCode){
		
		LOG.debug("Save partial project with name ({})", projectName);
		
		Project project = projectService.getProjectByName(projectName);
		
		project.setCustomCode(customCode);
		project.setLastModified(new Date());
		
		projectService.saveProject( project );
		
		LOG.debug("Updated project custom code mappings");
	}
	
	@RequestMapping(value="/save/datapoints/{projectName}",
			method={RequestMethod.PUT},  consumes={APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void save(@PathVariable String projectName, 
			@RequestBody List<ContainerViewComponent> dataPointMapping){
		
		LOG.debug("Save partial project with name ({})", projectName);
		
		Project project =  projectService.getProjectByName(projectName);
		
		project.setDataPointMapping(dataPointMapping);
		project.setLastModified(new Date());
		
		projectService.saveProject( project );
		
		LOG.debug("Updated project datapoints Mapping");
	}
	
	@RequestMapping(value="/save/nmm/{projectName}",
			method={RequestMethod.PUT},  consumes={APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void save(@PathVariable String projectName, 
			@RequestBody NMMXMLInformation nmmMapping){
		
		LOG.debug("Save partial project with name ({})", projectName);
		
		Project project = projectService.getProjectByName(projectName);
		
		project.setNmmMapping(nmmMapping);
		project.setLastModified(new Date());
		
		projectService.saveProject( project );
		
		LOG.debug("Updated project NMM mappings");
	}
	
	@RequestMapping(value="/save/obwi/{projectName}",
			method={RequestMethod.PUT},  consumes={APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void save(@PathVariable String projectName, 
			@RequestBody OBWIConfiguration obwiMapping){
		
		LOG.debug("Save partial project with name ({})", projectName);
		
		Project project = projectService.getProjectByName(projectName);
		
		project.setObwiMapping(obwiMapping);
		project.setLastModified(new Date());
		
		projectService.saveProject( project );
		
		LOG.debug("Updated project OBWI mappings");
	}
	
	@RequestMapping(value="/save/snmp/{projectName}",
			method={RequestMethod.PUT},  consumes={APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void save(@PathVariable String projectName, 
			@RequestBody SNMPConfiguration snmpMapping){
		
		LOG.debug("Save partial project with name ({})", projectName);
		
		Project project = projectService.getProjectByName(projectName);
		
		project.setSnmpMapping(snmpMapping);
		project.setLastModified(new Date());
		
		projectService.saveProject( project );
		
		LOG.debug("Updated project SNMP mappings");
	}
	
	@RequestMapping(value="/save/traps/{projectName}",
			method={RequestMethod.PUT},  consumes={APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void save(@PathVariable String projectName, 
			@RequestBody TrapsInformation trapsMapping){
		
		LOG.debug("Save partial project with name ({})", projectName);
		
		Project project = projectService.getProjectByName(projectName);
		
		project.setTrapsMapping(trapsMapping);
		project.setLastModified(new Date());
		
		projectService.saveProject( project );
		
		LOG.debug("Updated project traps mappings");
	}
	
	@RequestMapping(value="/resources/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public Map<ResourceKeys, List<FileResource>> showResources(@PathVariable String projectName){
		LOG.debug("Process show resources for project ({})", projectName);
		
		projectService.verifyProjectExists(projectName);
		
		EnumMap<ResourceKeys, List<FileResource>> resources = 
				new EnumMap<>(ResourceKeys.class);
		
		Path helpDirPath = projectPackagingService.getProjectHelpBaseDirPath(projectName);
		Path resDirPath = projectPackagingService.getProjectResourceBundleBaseDirPath(projectName);
		Path imagesDirPath = projectPackagingService.getProjectImageBaseDirPath(projectName);
		Path customCodeDirPath = projectPackagingService.getProjectCustomCodeDirPath(projectName);
		
		try (Stream<Path> helpFiles = Files.walk(helpDirPath);
			 Stream<Path> imageFiles = Files.walk(imagesDirPath);
			 Stream<Path> resFiles = Files.walk(resDirPath);
			 Stream<Path> customCodeFiles = Files.walk(customCodeDirPath)){
			
			resources.put(ResourceKeys.help, mapPathToFileResource(helpFiles));
			resources.put(ResourceKeys.images,  mapPathToFileResource(imageFiles));
			resources.put(ResourceKeys.resourceBundles, mapPathToFileResource(resFiles));
			resources.put(ResourceKeys.customCode, mapPathToFileResource(customCodeFiles));			
			
		} catch (IOException e) {
			LOG.error(String.format("Failed to iterate a directory for project (%s)",
					projectName), e);
			throw new DirectoryIteratorException(e);
		}
		
		return resources;
	}
	
	
	@RequestMapping(value="/nmmxml/common", method = RequestMethod.POST, 
			consumes={MULTIPART_FORM_DATA_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void uploadCommonDataFile(@RequestPart("commonData") Part commonDataFile,
					@RequestParam("name") String projectName) {
		
		LOG.debug("Upload common data file ({}) for project ({})", 
				commonDataFile.getSubmittedFileName(), projectName);
		
		projectService.verifyProjectExists(projectName);
		
		try(InputStream input = commonDataFile.getInputStream())
		{
			Files.copy(input, projectPackagingService.getProjectCommonDataDirPath(projectName 
					).resolve(commonDataFile.getSubmittedFileName()),
					 StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			LOG.error("Error processing common data file.", e);
			throw new ProjectUploadCommonDataFileException(projectName);
		}		
	}
	
	@RequestMapping(value="/customCode", method = RequestMethod.POST, 
			consumes={MULTIPART_FORM_DATA_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void uploadCustomCodeJarFile(@RequestPart("customJar") Part customCodeJarFile,
					@RequestParam("name") String projectName) {
		
		LOG.debug("Upload custom code jar file ({}) for project ({})", 
				customCodeJarFile.getSubmittedFileName(), projectName);
		
		projectService.verifyProjectExists(projectName);
		
		projectPackagingService.emptyCustomCodeDirectory(projectName);
		
		try(InputStream input = customCodeJarFile.getInputStream())
		{
			Files.copy(input, projectPackagingService.getProjectCustomCodeDirPath(projectName 
					).resolve(customCodeJarFile.getSubmittedFileName()),
					 StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			LOG.error("Error processing custom code jar file.", e);
			throw new ProjectUploadCommonDataFileException(projectName);
		}
	}
	
	@RequestMapping(value="/help", method = RequestMethod.POST, 
			consumes={MULTIPART_FORM_DATA_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public void uploadHelpFile(@RequestPart("helpFile") Part helpFile, 
			@RequestParam("name") String projectName){
		
		LOG.debug("Upload help file for project ({})", projectName);
		
		projectService.verifyProjectExists(projectName);
				
		try(DirectoryStream<Path> dir = Files.newDirectoryStream(
				projectPackagingService.getProjectHelpBaseDirPath(projectName))){
			
			if(dir.iterator().hasNext()){
				LOG.debug("Help file already uploaded. Clean directory before new upload");
				projectPackagingService.emptyProjectHelpDirectory(projectName);
			}
			Files.copy(helpFile.getInputStream(), 
					projectPackagingService.getProjectHelpBaseDirPath(
							projectName).resolve(helpFile.getSubmittedFileName()),
					REPLACE_EXISTING);
		} catch (IOException e) {
			LOG.error("Failed to upload help file", e);
			throw new ProjectHelpFileUploadException(projectName, e);
		}
	}
	
	@RequestMapping(value="/image", method = RequestMethod.POST, 
			consumes={MULTIPART_FORM_DATA_VALUE, IMAGE_GIF_VALUE, 
			IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public void uploadImage(@RequestPart("image") Part image,
			@RequestParam("name") String projectName){
		
		projectService.verifyProjectExists(projectName);

		Path imageFile = projectPackagingService.getProjectImageFilePath(projectName,
			image.getSubmittedFileName());
		try{
			Files.copy(image.getInputStream(), imageFile, REPLACE_EXISTING);
		} catch (IOException e) {
			LOG.error("Failed to upload image", e);
				throw new ProjectImageUploadException(projectName, e);
		}		
	}
	
	@RequestMapping(value="/mibFile", method = RequestMethod.POST, 
			consumes={MULTIPART_FORM_DATA_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void uploadMibFile(@RequestPart("mibFile") Part mibFile,
					@RequestParam("name") String projectName) {
		
		LOG.debug("Upload additional mib file ({}) for project ({})", 
				mibFile.getSubmittedFileName(), projectName);
		
		Project project = projectService.getProjectByName(projectName);
		
		try(InputStream input = mibFile.getInputStream())
		{
			MibTree mibTree = projectPackagingService.saveMibFile(projectName, 
					mibFile.getSubmittedFileName(), input, servletContext.getContextPath(),
					false); 
			project.setLastModified(new Date());	
			project.addMibTree(mibFile.getSubmittedFileName(), mibTree);
			projectService.saveProject(project);	
		} catch (IOException e) {
			LOG.error("Error processing saving MIB file.", e);
			throw new ProjectUploadMibFileException(projectName);
		}		
	}
	
	@RequestMapping(value="/nmmxml", method = RequestMethod.POST, 
			consumes={MULTIPART_FORM_DATA_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void uploadNmmXmlFile(@RequestPart("nmmXml") Part nmmXmlFile,
					@RequestParam("name") String projectName) {
		
		LOG.debug("Upload NMM XML file ({}) for project ({})", 
				nmmXmlFile.getSubmittedFileName(), projectName);
		
		projectService.verifyProjectExists(projectName);
		
		Path temp = null;
		try(InputStream input = nmmXmlFile.getInputStream())
		{
			temp = Paths.get(projectPackagingService.getTempBaseDirPath().toString()
					).resolve(nmmXmlFile.getSubmittedFileName() + "_temp.xml");
			Files.copy(input, temp);
			if(!projectPackagingService.validateNmmXmlFileContainsNmmApiType(temp.toFile())){
				throw new InvalidNmmXmlFileException(projectName,
						nmmXmlFile.getSubmittedFileName());
			}
		 
			Files.copy(temp, projectPackagingService.getProjectXmlNmmDirPath(projectName 
					).resolve(nmmXmlFile.getSubmittedFileName()),
					 StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			LOG.error("Error processing NXMM XML file.", e);
			throw new ProjectUploadNmmXmlFileException(projectName);
		} finally{
			if( null != temp){
				try {
					Files.delete(temp);
				} catch (IOException IGNORE) {}
			}
		}
	}
	
	@RequestMapping(value="/bundle", method = RequestMethod.POST, 
			consumes={MULTIPART_FORM_DATA_VALUE, TEXT_PLAIN_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public void uploadResourceBundle(@RequestPart("resourceBundle") Part resourceBundle, 
			@RequestParam("name") String projectName){
		
		projectService.verifyProjectExists(projectName);
		
		Path resourceBundleFile = projectPackagingService.getProjectResourceBundlePath(projectName, 
				resourceBundle.getSubmittedFileName());		
		try(Writer writer = new FileWriter(resourceBundleFile.toFile())) {
			Properties props = new Properties();
			props.load(resourceBundle.getInputStream());
			if(props.keySet().size() <  1){
				throw new IllegalArgumentException(
						messageSoruce.getMessage("project.inavlid.resource.bundle.error",
								new Object[]{resourceBundle.getSubmittedFileName()},
								RequestContextUtils.getLocale(httpServletRequest)));
			}
			
			props.store(writer, "Project (" + projectName + 
					") property file " + resourceBundle.getSubmittedFileName());
		} catch (IOException|IllegalArgumentException e) {
			LOG.error("Failed to upload resource bundle", e);
			throw new ProjectResourceBundleUploadException(projectName, e);
		}		
	}
	
	@RequestMapping(value="/validate/customCode/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public List<String> validateCustomCodeMappings(@PathVariable String projectName){
		
		Project project = projectService.getProjectByName(projectName);
		
		Set<ConstraintViolation<CustomCodeInformation>> errors = 
				jsr303Validator.validate(project.getCustomCode());
		
		return errors.stream()
		      .map(ConstraintViolation::getMessage)
		      .collect(Collectors.toList());
	}
	
	@RequestMapping(value="/validate/datapoints/{projectName}/{panelName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public Set<String> validateDatapointMappings(@PathVariable String projectName,
			@PathVariable String panelName){		
		Project project = projectService.getProjectByName(projectName);
		Set<String> result = new HashSet<>();
		
		project.getDataPointMapping()
				.stream()
				.filter(container-> container.getName().equals(panelName))
				.flatMap(container -> container.getGroups().stream())				
				.flatMap(group-> group.getElements().stream())
				.forEach(element-> {					
					Set<ConstraintViolation<ElementViewComponent>> validationResults = jsr303Validator
							.validate(element);
					if (validationResults.size() > 0) {
						Set<String> errors = validationResults.stream()
								.map(error -> error.getMessage())
								.collect(Collectors.toSet());
						result.addAll(errors);
					}});
		
		return result;
	}
	
	
	@RequestMapping(value="/validate/nmm/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public List<String> validateNmmMappings(@PathVariable String projectName){
		
		Project project = projectService.getProjectByName(projectName);
		
		Set<ConstraintViolation<NMMXMLInformation>> errors = 
				jsr303Validator.validate(project.getNmmMapping());
		
		return errors.stream()
		      .map(ConstraintViolation::getMessage)
		      .collect(Collectors.toList());
	}
	
	@RequestMapping(value="/validate/obwi/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public List<String> validateOBWIMappings(@PathVariable String projectName){
		
		Project project = projectService.getProjectByName(projectName);
		
		Set<ConstraintViolation<OBWIConfiguration>> errors = 
				jsr303Validator.validate(project.getObwiMapping());
		
		return errors.stream()
		      .map(ConstraintViolation::getMessage)
		      .collect(Collectors.toList());
	}
	
	@RequestMapping(value="/validate/snmp/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public List<String> validateSnmpMappings(@PathVariable String projectName){
		
		Project project = projectService.getProjectByName(projectName);
		
		Set<ConstraintViolation<SNMPConfiguration>> errors = 
				jsr303Validator.validate(project.getSnmpMapping());
		
		return errors.stream()
		      .map(ConstraintViolation::getMessage)
		      .collect(Collectors.toList());
	}
	
	@RequestMapping(value="/validate/traps/{projectName}",
			method={RequestMethod.GET},  produces={APPLICATION_JSON_VALUE})
	public List<String> validateTrapsMappings(@PathVariable String projectName){
		
		Project project = projectService.getProjectByName(projectName);
		
		Set<ConstraintViolation<TrapsInformation>> errors = 
				jsr303Validator.validate(project.getTrapsMapping());
		
		return errors.stream()
		      .map(ConstraintViolation::getMessage)
		      .collect(Collectors.toList());
	}
}
