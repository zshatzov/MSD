package com.avocent.plugins.generator.web.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.avocent.plugins.generator.ImageDownloadException;
import com.avocent.plugins.generator.InvalidNmmXmlFileException;
import com.avocent.plugins.generator.MibDetailNotFoundException;
import com.avocent.plugins.generator.MibTreeNotFoundException;
import com.avocent.plugins.generator.PluginGenerationException;
import com.avocent.plugins.generator.ProjectAlreadyExistsException;
import com.avocent.plugins.generator.ProjectCreateException;
import com.avocent.plugins.generator.ProjectFileDownloadException;
import com.avocent.plugins.generator.ProjectHelpFileUploadException;
import com.avocent.plugins.generator.ProjectImageUploadException;
import com.avocent.plugins.generator.ProjectLoadResourceBundleException;
import com.avocent.plugins.generator.ProjectNotFoundException;
import com.avocent.plugins.generator.ProjectResourceBundleUploadException;
import com.avocent.plugins.generator.ProjectUploadCommonDataFileException;
import com.avocent.plugins.generator.ProjectUploadCustomCodeFileException;
import com.avocent.plugins.generator.ProjectUploadMibFileException;
import com.avocent.plugins.generator.ProjectUploadNmmXmlFileException;
import com.avocent.plugins.generator.model.MibTree;
import com.avocent.plugins.generator.model.Project;
import com.avocent.plugins.generator.model.ProjectStatus;
import com.avocent.plugins.generator.model.ProjectType;
import com.avocent.plugins.generator.model.common.IdentitySection;
import com.avocent.plugins.generator.model.common.NMMXMLInformation;
import com.avocent.plugins.generator.model.common.OBWIConfiguration;
import com.avocent.plugins.generator.model.common.SNMPConfiguration;
import com.avocent.plugins.generator.model.common.TrapsInformation;
import com.avocent.plugins.generator.model.view.ContainerViewComponent;
import com.avocent.plugins.generator.service.DeviceViewService;
import com.avocent.plugins.generator.service.MibDetailProcessor;
import com.avocent.plugins.generator.service.MibNodeJsonConverter;
import com.avocent.plugins.generator.service.ProjectDatapointPopulater;
import com.avocent.plugins.generator.service.ProjectLinks;
import com.avocent.plugins.generator.service.ProjectPackagingService;
import com.avocent.plugins.generator.service.ProjectService;
import com.avocent.plugins.generator.service.XMLFileGeneratorService;
import com.avocent.plugins.generator.web.FileResource;

public abstract class BaseRestController {
	
	private enum MessageKey{ message }
	
	protected enum ResourceKeys{customCode, help, images, resourceBundles}
	
	@Autowired
	protected MibDetailProcessor mibDetailProcessor;
	
	@Autowired
	protected MibNodeJsonConverter mibNodeJsonConverter;
	
	@Autowired
	protected DeviceViewService deviceViewService;
	
	@Autowired
	protected MibNodeJsonConverter converter;
	
	@Autowired
	protected XMLFileGeneratorService fileGenerator;
	
	@Autowired
	protected ProjectPackagingService projectPackagingService;
	
	@Autowired
	protected ProjectService projectService;
	
	@Autowired
	protected ResourceBundleMessageSource messageSoruce;	

	@Autowired
	protected ServletContext servletContext;
	
	@Autowired
	protected ResourceBundleMessageSource messageSource;
	
	@Autowired
	protected HttpServletRequest httpServletRequest;
	
	@Autowired
	protected Validator jsr303Validator;
	
	@Autowired
	protected ProjectLinks projectLinks;
	
	@Autowired
	protected ProjectDatapointPopulater projectDatapointPopulater;
	
	protected final Logger LOG = 
			LoggerFactory.getLogger(getClass());
	
	protected Project createProject(MibTree primaryMibTree, String mibFileName, 
			String projectName, ProjectType type){

		Project project = new Project(projectName);
		
		project.setType(type);
		project.setStatus(ProjectStatus.PARTIAL);			
		project.addMibTree(mibFileName, primaryMibTree);
		project.setLastModified(new Date());	
		
		LOG.debug("Initalize datapoints...");
		List<ContainerViewComponent> datapoints =
				deviceViewService.getDatapointsByProjectType(type);
		project.setDataPointMapping(datapoints);
		
		LOG.debug("Initalize NMM Mappings...");
		NMMXMLInformation nmmMapping = new NMMXMLInformation();
		IdentitySection identitySection = new IdentitySection();	 
		identitySection.setImplementationClass("com.avocent.seedPlugin.seedPduPlugin");
		nmmMapping.setIdentitySection(identitySection);
		project.setNmmMapping(nmmMapping);
		
		LOG.debug("Initalize OWBI...");
		project.setObwiMapping(new OBWIConfiguration());
		
		LOG.debug("Initalize SNMP...");
		project.setSnmpMapping(new SNMPConfiguration());
		
		LOG.debug("Initalize Traps...");
		TrapsInformation traps = 
				mibNodeJsonConverter.mibNodeToTrapsInformation(
						primaryMibTree.getMibTreeObject(),	type);		
		project.setTrapsMapping(traps);
		
		return project;
	}
	
	@ExceptionHandler(ProjectFileDownloadException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	Map<MessageKey, String> downloadProjectArtifactsError(ProjectFileDownloadException e){	
		EnumMap<MessageKey, String> error = new EnumMap<>(MessageKey.class);
		error.put(MessageKey.message, messageSource.getMessage(
						"project.xml.file.download.error", new Object[]{
							e.getProjectName(),	e.getCause().getMessage()},
						RequestContextUtils.getLocale(httpServletRequest)));
		
		return error;
	}
	
	@ExceptionHandler(PluginGenerationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	Map<MessageKey, String> generatePluginError(PluginGenerationException e){
		EnumMap<MessageKey, String> error = new EnumMap<>(MessageKey.class);
		error.put(MessageKey.message, messageSource.getMessage(
						"project.generate.custom.plugin.error",new Object[]{
								e.getProjectName(), e.getMessage()},								
						RequestContextUtils.getLocale(httpServletRequest)));
		
		return error;
	}
	
	@ExceptionHandler(ProjectHelpFileUploadException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	Map<MessageKey, String> helpFileUploadError(ProjectHelpFileUploadException e){	
		EnumMap<MessageKey, String> error = new EnumMap<>(MessageKey.class);
		error.put(MessageKey.message, messageSource.getMessage(
						"project.help.file.upload.error",new Object[]{
								e.getProjectName(), e.getCause().getMessage()},
						RequestContextUtils.getLocale(httpServletRequest)));
		
		return error;
	}
	
	@ExceptionHandler(ImageDownloadException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	Map<MessageKey, String>  imageDownloadError(ImageDownloadException e){
		EnumMap<MessageKey, String> error = new EnumMap<>(MessageKey.class);
		error.put(MessageKey.message,  messageSource.getMessage(
						"project.image.download.error",new Object[]{
							e.getImageName(), e.getProjectName(), e.getCause().getMessage()},
						RequestContextUtils.getLocale(httpServletRequest)));
		
		return error;
	}
	
	@ExceptionHandler(ProjectImageUploadException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	Map<MessageKey, String> imageUploadError(ProjectImageUploadException e){	
		EnumMap<MessageKey, String> error = new EnumMap<>(MessageKey.class);
		error.put(MessageKey.message, messageSource.getMessage(
						"project.image.file.upload.error",new Object[]{ 
								e.getProjectName(), e.getCause().getMessage()},
						RequestContextUtils.getLocale(httpServletRequest)));
		
		return error;
	}
	
	@ExceptionHandler(ProjectCreateException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	Map<MessageKey, String> invalidMibFileError(ProjectCreateException e){
		EnumMap<MessageKey, String> error = new EnumMap<>(MessageKey.class);
		error.put(MessageKey.message, messageSource.getMessage(
						"project.mibfile.error",new Object[]{ e.getProjectName()},
						RequestContextUtils.getLocale(httpServletRequest)));
		
		return error;
	}
	
	@ExceptionHandler(InvalidNmmXmlFileException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	Map<MessageKey, String> invalidNmmXmlFileError( 
			InvalidNmmXmlFileException e){
		EnumMap<MessageKey, String> error = new EnumMap<>(MessageKey.class); 
		String message = messageSoruce.getMessage("project.nmmxml.file.missing.nmmApiType.error",
				new Object[]{e.getNmmXmlFileName(), e.getProjectName()},
				RequestContextUtils.getLocale(httpServletRequest));
		
		error.put(MessageKey.message, message);
		
		return error;	
	}
	
	/**
	 * A utility method to map a {@link Stream} of path objects (representing a file)
	 * to a {@link FileResource} objects.
	 * 
	 * @param stream
	 * @return
	 */
	List<FileResource> mapPathToFileResource(Stream<Path> stream){
		return stream.filter(path -> Files.isRegularFile(path))
		.map(path -> new FileResource(
				path.getFileName().toString(), 
				projectPackagingService.getLastModifiedDate(path)))
		.collect(Collectors.toList());
	}
	
	@ExceptionHandler(MibDetailNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	Map<MessageKey, String> mibDetailNotFoundError(MibDetailNotFoundException e){
		EnumMap<MessageKey, String> error = new EnumMap<>(MessageKey.class);
		error.put(MessageKey.message, messageSource.getMessage(
						"mib.detail.not.found.error",new Object[]{
							e.getMibOid(), e.getProjectName()},
						RequestContextUtils.getLocale(httpServletRequest)));
		
		return error;
	}
	
	@ExceptionHandler(MibTreeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	Map<MessageKey, String> mibTreeNotFoundError(MibTreeNotFoundException e){
		EnumMap<MessageKey, String> error = new EnumMap<>(MessageKey.class);
		error.put(MessageKey.message, messageSource.getMessage(
						"mib.tree.not.found.error",new Object[]{
							e.getMibFileName(), e.getProjectName()},
						RequestContextUtils.getLocale(httpServletRequest)));
		
		return error;
	}
	
	@ExceptionHandler(ProjectAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	Map<MessageKey, String> projectAlreadyExistsError(ProjectAlreadyExistsException e){
		LOG.error("Project with name ({}) already exists", e.getProjectName());
		EnumMap<MessageKey, String> error = new EnumMap<>(MessageKey.class);
		error.put(MessageKey.message, messageSource.getMessage(
						"project.already.exists.error",new Object[]{e.getProjectName()},
						RequestContextUtils.getLocale(httpServletRequest)));
		
		return error;
	}
	
	@ExceptionHandler(ProjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	Map<MessageKey, String> projectNotFoundError(ProjectNotFoundException e){
		LOG.error("Project with name ({}) not found", e.getProjectName());
		EnumMap<MessageKey, String> error = new EnumMap<>(MessageKey.class);
		error.put(MessageKey.message, messageSource.getMessage(
						"project.not.found.error",new Object[]{e.getProjectName()},
						RequestContextUtils.getLocale(httpServletRequest)));
		
		return error;
	}
	
	@ExceptionHandler(ProjectLoadResourceBundleException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	Map<MessageKey, String>  resourceBundleLoadError(ProjectLoadResourceBundleException e){	
		EnumMap<MessageKey, String> error = new EnumMap<>(MessageKey.class);
		error.put(MessageKey.message, messageSource.getMessage(
						"project.load.resource.bundle.error",new Object[]{
							e.getProjectName(),	e.getCause().getMessage()},
						RequestContextUtils.getLocale(httpServletRequest)));
		
		return error;
	}
	
	@ExceptionHandler(ProjectResourceBundleUploadException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	Map<MessageKey, String> resourceBundleUploadError(ProjectResourceBundleUploadException e){	
		EnumMap<MessageKey, String> error = new EnumMap<>(MessageKey.class);
		error.put(MessageKey.message, messageSource.getMessage(
						"project.resource.bundle.upload.error",new Object[]{
								e.getProjectName(), e.getCause().getMessage()},
						RequestContextUtils.getLocale(httpServletRequest)));
		
		return error;
	}
	
	@ExceptionHandler(ProjectUploadCommonDataFileException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	Map<MessageKey, String> uploadCommonDataFileError( 
			ProjectUploadCommonDataFileException e){
		EnumMap<MessageKey, String> error = new EnumMap<>(MessageKey.class);
		error.put(MessageKey.message, messageSource.getMessage(
						"project.upload.common.data.file.error",new Object[]{
								e.getProjectName(), e.getCause().getMessage()},
						RequestContextUtils.getLocale(httpServletRequest)));
		
		return error;	
	}
	
	@ExceptionHandler(ProjectUploadCustomCodeFileException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	Map<MessageKey, String> uploadCustomCodeFileError( 
			ProjectUploadCustomCodeFileException e){
		EnumMap<MessageKey, String> error = new EnumMap<>(MessageKey.class);
		error.put(MessageKey.message, messageSource.getMessage(
						"project.upload.custom.code.file.error",new Object[]{
								e.getProjectName(), e.getCause().getMessage()},
						RequestContextUtils.getLocale(httpServletRequest)));
		
		return error;	
	}
	
	
	@ExceptionHandler(ProjectUploadMibFileException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	Map<MessageKey, String> uploadMibFileError(ProjectUploadMibFileException e){
		EnumMap<MessageKey, String> error = new EnumMap<>(MessageKey.class);
		error.put(MessageKey.message, messageSource.getMessage(
						"project.upload.mib.file.error",new Object[]{
								e.getProjectName(), e.getCause().getMessage()},
						RequestContextUtils.getLocale(httpServletRequest)));
		
		return error;	
	}
	
	@ExceptionHandler(ProjectUploadNmmXmlFileException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	Map<MessageKey, String> uploadNmmXmlFileError(ProjectUploadNmmXmlFileException e){
		EnumMap<MessageKey, String> error = new EnumMap<>(MessageKey.class);
		error.put(MessageKey.message, messageSource.getMessage(
						"project.upload.nmmxml.file.error",new Object[]{
								e.getProjectName(), e.getCause().getMessage()},
						RequestContextUtils.getLocale(httpServletRequest)));
		
		return error;	
	}
}
