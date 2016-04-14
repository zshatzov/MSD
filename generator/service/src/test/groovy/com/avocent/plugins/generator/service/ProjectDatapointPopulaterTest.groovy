package com.avocent.plugins.generator.service

import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import com.avocent.plugins.generator.config.TestConfig
import com.avocent.plugins.generator.model.Project
import com.avocent.plugins.generator.model.ProjectType


@ContextConfiguration(classes = [TestConfig])
@RunWith(SpringJUnit4ClassRunner)
class ProjectDatapointPopulaterTest {
	
	@Autowired
	ProjectDatapointPopulater service
	
	File mappingsFilePath
	
	Project pduProject
	
	@Before
	void setup(){
		mappingsFilePath = new ClassPathResource('PDUMappings.xml').file
		pduProject = new Project('PDUPlugin')
	}
	

	@Test
	public void retrieveProjectType() {
		ProjectType type = service.retrieveProjectType(mappingsFilePath)
		
		assert type == ProjectType.PDU
	}

	@After
	void cleanup(){
		mappingsFilePath = null	
		pduProject = null
	}	
}
