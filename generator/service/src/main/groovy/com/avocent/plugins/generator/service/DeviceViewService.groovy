package com.avocent.plugins.generator.service

import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j

import javax.annotation.Resource

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource as SpringResource
import org.springframework.stereotype.Service

import com.avocent.plugins.generator.dao.DeviceViewRepository
import com.avocent.plugins.generator.model.DeviceView
import com.avocent.plugins.generator.model.ProjectType
import com.avocent.plugins.generator.model.view.ContainerViewComponent
import com.mongodb.util.JSON

@Service
@Slf4j('LOG')
class DeviceViewService {
	
	@Resource(name="jsonFiles")
	private List<SpringResource> jsonFiles
	
	@Autowired
	private DeviceViewRepository deviceViewRepository
	
	List<ContainerViewComponent> getDatapointsByProjectType(ProjectType type){
		def view = deviceViewRepository.findByProjectType(type).getView()	
		JsonSlurper slurper = new JsonSlurper()
		slurper.parseText(JSON.serialize(view)) 
	}
	
	
	DeviceView getDeviceViewByType(ProjectType projectType){
		deviceViewRepository.findByProjectType(projectType)
	}
	
	List<DeviceView> retrieveAll(){			
		jsonFiles.inject([]){views, SpringResource resource-> 
			def matcher = resource.filename =~ /(.+)\.json/			
			def deviceType = matcher[0][1]
			DeviceView deviceView = new DeviceView(
				projectType: ProjectType.valueOf(deviceType),
				view: JSON.parse(resource.inputStream.text))
			views << deviceView
		} 
	}
	
	List<String> supportedProjectTypes(){
		jsonFiles.collect{
			(it.filename =~ /(.+)\.json/)[0][1]
		}
	}
} 