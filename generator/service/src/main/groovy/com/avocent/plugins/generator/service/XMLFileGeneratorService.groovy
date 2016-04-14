package com.avocent.plugins.generator.service

import groovy.util.logging.Slf4j

import java.util.concurrent.locks.ReentrantReadWriteLock

import javax.xml.transform.stream.StreamResult

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.oxm.jaxb.Jaxb2Marshaller
import org.springframework.stereotype.Service

import com.avocent.plugins.generator.model.Project

@Service
@Slf4j('LOG')
class XMLFileGeneratorService {

	@Autowired	
	ProjectPackagingService projectPackagingService
	
	@Autowired
	Jaxb2Marshaller jaxb2Marshaller
	
	private final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock()

	boolean generateFile(final Project project){
		
		LOG.debug("Generate XML file for project ({})", project.name)		
		
		def handler = MappingHandlerFactory.getHanlder(project.type)
		
		def jaxbObject = handler.mapObject(project.dataPointMapping)
		jaxbObject['trapsInformation'] = project?.trapsMapping
		jaxbObject['obwiConfiguration'] = project?.obwiMapping
		jaxbObject['snmpConfiguration'] = project?.snmpMapping
		jaxbObject['nmmxmlInformation'] = project?.nmmMapping
		jaxbObject['customCodeInformation'] = project?.customCode
		
		LOCK.writeLock().lock()
		try{				
			
		    projectPackagingService.getProjectGeneratedXmlFilePath(project)?.withWriter('UTF-8'){writer->
				jaxb2Marshaller.marshal(jaxbObject, new StreamResult(writer))
			}
									
			return true
		}catch(Exception  e){			
		    LOG.info("Error generating XML file: ${e.message}")
			return false
		}finally{ 
			LOCK.writeLock().unlock()
		}
	}
}
