package com.avocent.plugins.generator.web.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avocent.plugins.generator.model.DeviceView;
import com.avocent.plugins.generator.model.ProjectType;
import com.mongodb.util.JSON;

/**
 * <p>
 *  A <em>Rest</em> service that exposes the device views
 *  supported by the plugin generator. The  {@link DeviceView} represents
 *  the datapoint mapping between a MIB trap and a specific device datapoint. 
 * </p> 
 * 
 * @author zshatzov
 *
 */
@RestController
@RequestMapping("/device")
public class ViewRestController extends BaseRestController{
	
	@RequestMapping(value="/view",
			method={RequestMethod.GET}, produces={APPLICATION_JSON_VALUE})
	public List<ProjectType> retrieveAll(){
		
		LOG.debug("Retrieve all supported device views");
		
		List<ProjectType> types = deviceViewService.retrieveAll()
			.stream()
			.map((DeviceView view) -> view.getProjectType())
			.collect(Collectors.toList());
		
		return types;
	}
	
	@RequestMapping(value="/view/{projectType}",
			method={RequestMethod.GET}, produces={APPLICATION_JSON_VALUE})
	public String retrieveByProjectType(
			@PathVariable ProjectType projectType){		
		LOG.debug("Retrieve device view by projectType => {}", projectType);
		
		DeviceView deviceView = deviceViewService.getDeviceViewByType(projectType);
		return JSON.serialize(deviceView.getView());
	}
}
