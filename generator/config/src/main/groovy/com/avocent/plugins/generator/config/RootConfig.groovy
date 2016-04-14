package com.avocent.plugins.generator.config

import groovy.util.logging.Slf4j

import java.nio.file.Path
import java.nio.file.Paths

import javax.annotation.PostConstruct
import javax.xml.XMLConstants

import org.apache.tools.ant.Project
import org.apache.tools.ant.Target
import org.apache.tools.ant.taskdefs.Get
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.oxm.jaxb.Jaxb2Marshaller

@Configuration
@Slf4j('LOG')
class RootConfig {

	@Bean(name="jsonFiles")
	List<Resource> jsonFiles(){
		['PDU.json', 'UPS.json'].collect{new ClassPathResource(it)}
	}	 
	
	@Bean
	Path baseDirPath(){
		return Paths.get(System.properties['user.home']
			).resolve('Avocent').resolve('artifacts')
	}
	
	@Bean
	Jaxb2Marshaller jaxbContext(){		
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller()
		jaxb2Marshaller.setPackagesToScan('com.avocent.plugins.generator.model.pdu', 
			'com.avocent.plugins.generator.model.ups')
		jaxb2Marshaller.setSchemas(new ClassPathResource('PDUMibMapping.xsd') ,
			new ClassPathResource('UPSMibMapping.xsd'))
		jaxb2Marshaller.setSchemaLanguage(XMLConstants.W3C_XML_SCHEMA_NS_URI)	
		
		return jaxb2Marshaller
	}	
	
	@Bean	
	Project antProject(Path baseDir) throws Exception{
				
		Project antProject = new Project(baseDir: baseDir.toFile(),
			name: 'Plugin Generator Project', description: 'Package a plugin as a jar file',
			'default': 'jar')
		/**
		 * Add ant task to use a get to retrieve a zip file
		 */
		Get get = new Get()
		get.setTaskType('Get')
		get.setTaskName('installSeedPlugin')	
		get.setProject(antProject)
		
		Target getSeedPluginTarget = new Target()
		getSeedPluginTarget.addTask(get)
		
		antProject.addTarget('getSeedPlugin', getSeedPluginTarget)
		
		return antProject
	}
	
	@PostConstruct
	void initalize(){		
		LOG.debug('Start creating CPG Tool directory structure')							
			
		AntBuilder ant = new AntBuilder() 
		
		Path baseDir = baseDirPath()
		
		ant.mkdir(dir: baseDir.resolve('projects').toFile())
		ant.mkdir(dir: baseDir.resolve('seedPlugin').toFile())
		jsonFiles().each{
			def matcher = it.filename =~ /(.+)\.json/
			def deviceType = matcher[0][1]
			ant.mkdir(dir: baseDir.resolve('seedPlugin').resolve(deviceType).toFile())
		}
		ant.mkdir(dir: baseDir.resolve('dist').toFile())	
		ant.mkdir(dir: baseDir.resolve('temp').toFile())
		ant.mkdir(dir: baseDir.resolve('logs').toFile())
		
		LOG.debug('Finished constructing CPG Tool directory structure')		
	}
}
