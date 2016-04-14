package com.avocent.plugins.generator.config

import static org.mockito.Mockito.*

import javax.validation.Validator

import org.apache.tools.ant.Project
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.oxm.jaxb.Jaxb2Marshaller

import com.avocent.plugins.generator.dao.DeviceViewRepository
import com.avocent.plugins.generator.dao.ProjectRepository
import com.avocent.plugins.generator.model.DeviceView
import com.avocent.plugins.generator.model.ProjectType
import com.avocent.plugins.generator.service.DeviceViewService
import com.avocent.plugins.generator.service.ProjectPackagingService
import com.mongodb.BasicDBList
import com.mongodb.util.JSON

@Configuration
@ComponentScan(basePackages=["com.avocent.plugins.generator.service"])
public class TestConfig {
	
	@Bean(name="jsonFiles")
	List<Resource> jsonFiles(){
		['PDU.json', 'UPS.json'].collect{
			[ 
				getFilename: {-> it }, 
			    getInputStream: {-> new ByteArrayInputStream('[{"hello": "world"}]'.bytes)}
			 ] as Resource
		}		 
	}
	
	
	@Bean
	DeviceViewRepository  deviceViewRepository(){
		Resource json = new ClassPathResource("PDU.json")
		['findByProjectType': {projectType ->
			new DeviceView(id: 1, projectType: ProjectType.PDU, view: 
				JSON.parse(json.file.text) as BasicDBList)
		}] as DeviceViewRepository
	}
	
	
	@Bean
	ProjectRepository projectRepoistory(){
		return mock(ProjectRepository)
	}
	
	
	@Bean
	ProjectPackagingService projectPackaingService(){
		return mock(ProjectPackagingService)
	}
	
	@Bean
	MongoTemplate mongoTemplate(){
		return mock(MongoTemplate)
	}
	
	@Bean
	Project antProject(){
		return mock(Project)
	}
	
	@Bean
	Validator jsr303Validator(){
		return mock(Validator)
	}
	
	@Bean
	Jaxb2Marshaller jaxb2Marshaller(){
		return mock(Jaxb2Marshaller)
	}
	
	@Bean
	DeviceViewService deviceViewService(){
		return mock(DeviceViewService)
	}
}