package com.avocent.plugins.generator.service

import static java.io.File.separatorChar
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING
import static org.apache.tools.ant.Project.MSG_INFO
import groovy.transform.TypeChecked
import groovy.transform.WithWriteLock;
import groovy.util.logging.Slf4j

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.regex.Pattern
import java.util.stream.Stream
import java.util.zip.ZipException
import java.util.zip.ZipFile

import org.apache.tools.ant.BuildException
import org.apache.tools.ant.BuildListener
import org.apache.tools.ant.Project as AntProject
import org.apache.tools.ant.ProjectHelper
import org.apache.tools.ant.Target
import org.apache.tools.ant.listener.SimpleBigProjectLogger
import org.apache.tools.ant.taskdefs.Copy
import org.apache.tools.ant.types.FileSet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service

import com.avocent.plugins.generator.model.MibTree
import com.avocent.plugins.generator.model.Project as CPGProject
import com.avocent.plugins.generator.model.ProjectType;
/**
 * <p>
 * A utility class for jar/zip/unzip various project and application artifacts.
 * 
 * This is accomplished using <em>Groovy's AntBuilder</em> helper class. 
 * </p>
 * 
 * @author zshatzov
 *
 */
@Service
@Slf4j('LOG')
class ProjectPackagingService {
	
	private static final String PLUGIN_PROPERTIES_FILENAME = 'plugin.properties'
	
	private static final String SEED_PLUGIN_PROP = 'seedPlugin.type'
	
	private static final Pattern PLUGIN_FILE_PATTERN = ~/^\w+\_(\d{1,})\.(\d{1,})\.(\d{1,})\.(\d{1,})$/
	
	private final static Pattern FILE_VERSION =  ~/^(\w+)\_(\d{1,}\.\d{1,}\.\d{1,}\.\d{1,})\.jar$/
	
	@Autowired
	MibProcessor mibProcessor
		
	@Autowired
	MibNodeJsonConverter converter
	
	@Autowired
	MibDetailProcessor mibDetailProcessor
	
	@Autowired
	AntProject antProject	

	@Autowired
	Environment env	
	@TypeChecked
	private Target copyProjectNmmXmlArtifacts(AntProject project,
				String projectName, Path dest){
		LOG.debug("Copy project (${projectName}) NMM XML artifacts to seed plugin")
		Copy copy = new Copy()
		copy.setFailOnError(true)
		copy.setOverwrite(true)
		copy.setTodir(dest.toFile())
		FileSet fileSet = new FileSet(dir: getProjectBaseDir(projectName).toFile())
		fileSet.setProject(project)
		fileSet.includes = 'xml/nmms/**/*'
		copy.addFileset(fileSet)
		copy.setProject(project)
	 
		Target copyArtifacts = new Target()
		copyArtifacts.addTask(copy)
		
		return copyArtifacts
	}
	
	void createProjectStructure(String projectName){
		LOG.debug("Create directory strcuture for project ({})", projectName)
	
		AntBuilder antBuilder = new AntBuilder()		
		antBuilder.mkdir(dir: getProjectResourceBundleBaseDirPath(projectName).toFile())
		antBuilder.mkdir(dir: getProjectImageBaseDirPath(projectName).toFile())
		antBuilder.mkdir(dir: getProjectHelpBaseDirPath(projectName).toFile())
		antBuilder.mkdir(dir: getProjectMibsBaseDirPath(projectName).toFile())
		antBuilder.mkdir(dir: getProjectTooloutputDirPath(projectName).toFile())
		antBuilder.mkdir(dir: getProjectCustomCodeDirPath(projectName).toFile())
		antBuilder.mkdir(dir: getProjectXmlNmmDirPath(projectName).toFile())
		antBuilder.mkdir(dir: getProjectCommonDataDirPath(projectName).toFile())
		antBuilder.mkdir(dir: getProjectDistDirPath(projectName).toFile())
		antBuilder.mkdir(dir: getProjectPluginsDistDirPath(projectName).toFile())
		
		LOG.debug("Successfully created directory strcuture for project ({})", projectName)		
	}
	
	void deleteFile(Path path){
		LOG.debug("Delete file ({})", path.fileName)
		
		if(!Files.isDirectory(path))		
		  new AntBuilder().delete(file: path.toFile(), quiet: true)
	}
	
	void deleteMibFile(String projectName, String mibFileName){
		LOG.debug("Delete mib file ({}) for project ({})", mibFileName, projectName)
		
		new AntBuilder().delete(quiet: true){
			fileset( dir:getProjectBaseDir(projectName).resolve('mibs').toFile(),
					includes: "${mibFileName}.*")
		}
	}
	
	void deleteProjectStructure(String projectName){
		
		LOG.debug("Delete project => ({}) directory structure", projectName)
			
		AntBuilder antBuilder = new AntBuilder()
		antBuilder.delete(includeEmptyDirs: true){
			fileset(dir: getProjectBaseDir(projectName).parent.toFile(), includes: "$projectName/**")
		}		
		antBuilder.delete(dir: getProjectDistDirPath(projectName).parent.toFile(), includeEmptyDirs: true)
	}
	
	private String determineProjectType(final ZipFile zip){
				
		def entry = zip.getEntry(PLUGIN_PROPERTIES_FILENAME)
		if(!entry){
			throw new ZipException("Zip file ${PLUGIN_PROPERTIES_FILENAME} is missing")
		}
		
		def type
		zip.getInputStream(entry).withReader {reader->
			def props = new Properties()
			props.load(reader)
			type = props.getProperty(SEED_PLUGIN_PROP)
		}
			
		return type
	}
	
	void emptyCustomCodeDirectory(String projectName){
		LOG.debug("Empty custom code directory")
	
		new AntBuilder().delete(includeEmptyDirs: true){
			fileset(dir: getProjectCustomCodeDirPath(projectName).toFile(),	includes: '**/*')
		}
	}
	
	void emptyProjectHelpDirectory(String projectName){
		if(Files.exists(getProjectHelpBaseDirPath(projectName))){
			LOG.debug("Empty help directory for project ({})", projectName)
			new AntBuilder().delete( includeEmptyDirs: true){
				fileset(dir: getProjectHelpBaseDirPath(projectName).toFile(), 
							includes: '**/*')		
			}
		}
	}	
	
	void emptyProjectTooloutpoutDirectory(String projectName){
		LOG.debug("Delete project generated XML File", projectName) 
		
		new AntBuilder().delete(dir: getProjectTooloutputDirPath(
			projectName).toFile(),	includes: '*.xml') 
	}
	
	@WithWriteLock
	void emptySeedPluginDirectory(String type){
		LOG.debug("Empty seed plugin directory")
		
		new AntBuilder().delete(includeEmptyDirs: true){
			fileset(dir: seedPluginBaseDir.resolve(type).toFile(), includes: '**/*')
		}
	}	
		
	@WithWriteLock
	void emptyTempDirectory(String projectName){	
		LOG.debug("Empty Temp directory")
	
		new AntBuilder().delete(includeEmptyDirs: true, quiet: true){
			fileset(dir: tempBaseDirPath.resolve(projectName).toFile(),	includes: '**/*')
		}
	}
	
	private BuildListener getBuildListener(){
		def buildListener = new SimpleBigProjectLogger()
		buildListener.outputPrintStream  = System.out
		buildListener.errorPrintStream = System.err
		buildListener.messageOutputLevel = MSG_INFO
		
		return buildListener
	}
	
	Path getDistDirPath(){
		antProject.baseDir.toPath().resolve('dist')
	}
	
	private String getHighestVersionedPluginArtifact(CPGProject project){

		Path projectPluginDistDir = getProjectPluginsDistDirPath(project.name)
		Stream<Path> stream =  Files.list(projectPluginDistDir)
		try{
			Optional<String> optional = stream
					.filter{it.fileName.toString().endsWith('.jar')}
					.map{it.fileName.toString() - '.jar'}
					.sorted{f1, f2->
						def v1 = f1.find(~/\d{1,}$/) as int
						def v2 = f2.find(~/\d{1,}$/) as int

						v2 <=> v1
					}
					.findFirst()

			def version = project.nmmMapping.identitySection.pluginVersion

			if(optional.present){
				/**
				 * Plugin was already generated for project, therefore increment 
				 * current version
				 */
				def matcher =  optional.get() =~ PLUGIN_FILE_PATTERN
				version = String.join('.', matcher[0][1],
						matcher[0][2], matcher[0][3], String.valueOf((matcher[0][4] as int) + 1))
			}

			return version
		}finally{
			if(stream){
				stream.close()
			}
		}
	}

	Date getLastModifiedDate(Path path){
		new Date(Files.getLastModifiedTime(path).toMillis())
	}
	
	Path getProjectBaseDir(String projectName){
		antProject.baseDir.toPath().resolve('projects').resolve(projectName)
	}
	
	Path getProjectCommonDataDirPath(String projectName){
		getProjectXmlNmmDirPath(projectName).resolve('common').resolve('data')
   }

	Path getProjectCustomCodeDirPath(String projectName){
		getProjectBaseDir(projectName).resolve('code')
	}
	
	Path getProjectDistDirPath(String projectName){
		getDistDirPath().resolve(projectName).resolve('project')
	}
	
	Path getProjectGeneratedXmlFilePath(CPGProject project){
		getProjectTooloutputDirPath(project.name).resolve("CPGMIBMappingFor${project.type}Schema.xml")
	}
	
	Path getProjectHelpBaseDirPath(String projectName){
		getProjectBaseDir(projectName).resolve('help')
	}
	
	Path getProjectImageBaseDirPath(String projectName){
		getProjectBaseDir(projectName).resolve('images')
	}
	
	Path getProjectImageFilePath(String projectName, String fileName){
		getProjectBaseDir(projectName).resolve('images').resolve(fileName)
	}
	
	Path getProjectMibsBaseDirPath(String projectName){
		getProjectBaseDir(projectName).resolve('mibs')
	}
	
	Path getProjectMibsFilePath(String projectName, String mibFile){
		getProjectBaseDir(projectName).resolve('mibs').resolve(mibFile)
	}
	
	Path getProjectPluginsDistDirPath(String projectName){
		getDistDirPath().resolve(projectName).resolve('plugins')
	}
	
	Path getProjectResourceBundleBaseDirPath(String projectName){
		getProjectBaseDir(projectName).resolve('res')
	}
	
	Path getProjectResourceBundlePath(String projectName, String fileName){
		getProjectBaseDir(projectName).resolve('res').resolve(fileName)
	}
	
	Path getProjectTooloutputDirPath(String projectName){
		getProjectBaseDir(projectName).resolve('tooloutput')
	}		
	
	
	Path getProjectXmlNmmDirPath(String projectName){
		 getProjectBaseDir(projectName).resolve('xml').resolve('nmms')
	}
	
	Path getSeedPluginBaseDir(){
		antProject.baseDir.toPath().resolve('seedPlugin')
	}	
		
	Path getTempBaseDirPath(){
		antProject.baseDir.toPath().resolve('temp')
	}
	
	boolean isSeedPluginInstalled(ProjectType type){
		Stream<Path> stream = Files.list(seedPluginBaseDir.resolve(type.toString()))
		try{
			long count = stream.count()
			LOG.debug("Seed plugin directory is empty {}", count > 0)
				
			return (count > 0)
		}finally{
			if(stream){
				stream.close()
			}
		}
	}
	
	void packageCustomPlugin(CPGProject project) throws BuildException{
		
		LOG.debug("Generate jar file for project {}", project.name)
		
		Path seedPluginPath = seedPluginBaseDir.resolve(project.type.toString())
		
		if(!isSeedPluginInstalled(project.type)){
			throw new BuildException('Seed plugin for (${project.type.toString()}) devices not installed')
		}
		
		emptyTempDirectory(project.name) 
				
		Path buildXmlPath = seedPluginPath.resolve('build').resolve('cpgbuild.xml')
		
		String version = getHighestVersionedPluginArtifact(project)
		LOG.info("Current highest versioned plugin artifact => ({})", version)
		
		AntProject antProject = new AntProject()
	
		antProject.addBuildListener(buildListener)
		antProject.baseDir = seedPluginPath.toFile()
		
		ProjectHelper projectHelper = ProjectHelper.getProjectHelper()
		String mibFileNames = project.mibTrees.collect{k, v-> k}.join(',')
		LOG.debug("mibFiles for project ${project.name} => $mibFileNames")		
		
		[
			mibFiles: mibFileNames,
			outputDir: getProjectPluginsDistDirPath(project.name).toString(), 
			'plugin.name': project.nmmMapping?.identitySection?.nmmId.replaceAll(/[\s|-]/, '_'), 
			'plugin.version': version, 
			'layout.path': tempBaseDirPath.resolve(project.name).resolve('layout').toString(), 
			'classes.path': tempBaseDirPath.resolve(project.name).resolve('classes').toString(),
			resDir: getProjectBaseDir(project.name).resolve('res').toString(),
			imageDir: getProjectBaseDir(project.name).resolve('images').toString(),
			helpDir: getProjectBaseDir(project.name).resolve('help').toString(),
			mibDir: getProjectBaseDir(project.name).resolve('mibs').toString(),
			tempDir: tempBaseDirPath.resolve(project.name).toString(),
			toolOutputDir: getProjectTooloutputDirPath(project.name).toString(),
			customJarsDir: getProjectCustomCodeDirPath(project.name).toString(),
			'java.install.path': System.getenv('JAVA_HOME')?:Paths.get(
								 System.properties['java.home']).parent.toString()						  
		].each{k, v-> antProject.setProperty(k, v)}			
		
		antProject.addReference("ant.projectHelper", projectHelper)
		projectHelper.parse(antProject, buildXmlPath.toFile())		
		
		Target copyNmmXmlArtifacts = copyProjectNmmXmlArtifacts(
				antProject, project.name, seedPluginPath)
		antProject.addTarget('copyNmmXmlArtifacts', copyNmmXmlArtifacts)	
				 
		antProject.executeTargets(['cleanForTool', 'copyNmmXmlArtifacts', 'fullBuild'] as Vector) 
		 	
		LOG.debug("Generated plugin jar file for project ({})",
			 getProjectDistDirPath(project.name))
	}
	
	String resolveResourceBundleBaseName(Path path){
		path.fileName.toString() - '.properties'
	}	
	
	String retrieveNmmApiType(String projectName, File nmmXmlFile){
		LOG.debug("Retrieve NmmAPIType value from NMM File (${nmmXmlFile.name})")		
		
		def line = nmmXmlFile.text.split("\n").find{it =~ /nmmApiType/}
		assert line, "File ${nmmXmlFile.name} must contain an id with type nmmApiType"
		
		return new XmlSlurper().parseText(line)
	}
	
	MibTree saveMibFile(String projectName, String submittedFileName, InputStream mib, 
			String webContextPath, boolean primaryMibTree)
	{
		LOG.debug("Save mib file ({})", submittedFileName)		
		Files.copy(mib, getProjectMibsBaseDirPath(projectName).resolve(submittedFileName),
			 StandardCopyOption.REPLACE_EXISTING)		
		mibProcessor.processMib(new FileReader(
			getProjectMibsFilePath(projectName, submittedFileName).toFile()))
		
		def dbObject = converter.mibNodeToJson(mibProcessor.root, webContextPath)
		MibTree mibTree = new MibTree(dbObject, primaryMibTree)
		mibDetailProcessor.process(mibProcessor.root, mibTree)
		
		mibTree
	}
				
	List<String> splitCustomPluginFileName(File jarFile){
		def matcher = jarFile.name =~ FILE_VERSION
		assert matcher.matches()
		
		String fileName = matcher[0][1]
		String fileVersion = matcher[0][2]
		
		[fileName[0..-1], fileVersion]
	}				

	void unzipHelpFile(String projectName, File  help){		
		LOG.debug("Unzipping file ({})", help.name)
		
		new AntBuilder().unzip(dest: getProjectBaseDir(projectName).toFile(), src :help)
	}				
				
	void unzipSeedPlugin(Path zipFilePath){		
		LOG.debug("Unzipping file ({})", zipFilePath.fileName)	
			
		def type = determineProjectType(new ZipFile(zipFilePath.toFile()))	
		
		assert type, "Property key ($SEED_PLUGIN_PROP) is missing from file ($PLUGIN_PROPERTIES_FILENAME)"
		
		Path dir = getSeedPluginBaseDir().resolve(type)
		if(!Files.exists(dir)){
			throw new ZipException("Devices with type ($type) are not supported")
		}
				
		if(isSeedPluginInstalled(type)){
		   emptySeedPluginDirectory(type)
		}			
		
		new AntBuilder().unzip(dest: dir.toFile(), src: zipFilePath.toFile(), 
			overwrite: true, failOnEmptyArchive: true)	
	}
	
	boolean validateNmmXmlFileContainsNmmApiType(File nmmXmlFile){
		def line = nmmXmlFile.text.split("\n").find{it =~ /nmmApiType/}
	 
		if( !line ){
			return false
		}else{
			return true
		}
	}
	
	
	void zipProjectArtifacts(CPGProject project){
		LOG.debug("Zip artifacts bound to project {}", project.name)
		
		new AntBuilder().zip( basedir: getProjectBaseDir(project.name).toFile(),
			destFile: getProjectDistDirPath(project.name).resolve("${project.name}.zip").toFile(),
			includes: "images/**, res/**, help/**, code/**, mibs/**, tooloutput/*.xml, xml/nmms/**" )
	}
}