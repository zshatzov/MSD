package com.avocent.plugins.generator.service

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import com.avocent.plugins.generator.config.TestConfig
import com.avocent.plugins.generator.model.ProjectType
import com.avocent.plugins.generator.model.view.ContainerViewComponent


@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
class DeviceViewServiceTest {

	@Autowired
	private DeviceViewService deviceViewService	

	@Test
	void getAllDeviceView(){
		def views = deviceViewService.retrieveAll()
		
		assert 2 == views.size()
		assert ProjectType.PDU in views*.projectType
		assert ProjectType.UPS in views*.projectType			
	}
	
	
	@Test
	void getDatapointsByProjectType(){
		 List<ContainerViewComponent> view = 
		   deviceViewService.getDatapointsByProjectType(ProjectType.PDU)
		   
		 assert view
		 
		 assert view.size() == 3
	}
}
