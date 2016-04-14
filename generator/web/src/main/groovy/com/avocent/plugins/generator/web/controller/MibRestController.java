package com.avocent.plugins.generator.web.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.avocent.plugins.generator.MibDetailNotFoundException;
import com.avocent.plugins.generator.MibTreeNotFoundException;
import com.avocent.plugins.generator.model.MibDetail;
import com.avocent.plugins.generator.model.MibTree;
import com.avocent.plugins.generator.model.Project;
import com.avocent.plugins.generator.web.MibTreeResource;
import com.mongodb.util.JSON;

@RestController
@RequestMapping("/mib")
public class MibRestController extends BaseRestController {
	
	@RequestMapping(method= RequestMethod.DELETE, value="/{projectName}/{mibFileName}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMibTreeForProject(@PathVariable String projectName, 
			@PathVariable String mibFileName){
		LOG.debug("Delete a mib file {()} for project ({})", mibFileName, 
				projectName);
		
		Project project = projectService.getProjectByName(projectName);
		MibTree mibTree = project.retrieveMibTreeByFileName(mibFileName);
		if(null == mibTree){
			throw new MibTreeNotFoundException(projectName, mibFileName);
		}
		
		if(!mibTree.isPrimary()){
			projectService.deleteMibTreeForProject(project, mibFileName);
		} 
	}	
	
	@RequestMapping(method= RequestMethod.GET, 
			value="/primary/{projectName}", produces={APPLICATION_JSON_VALUE})
	public String primaryMibTreeForProject(@PathVariable String projectName){
		
		LOG.debug("Retrieve primary MIB tree for project ({})", projectName);
		
		Project project = projectService.getProjectByName(projectName);  
	 
		return JSON.serialize(project.getPrimaryMibTree().getMibTreeObject()).replaceAll(" ", "");
	}
		
	@RequestMapping(method= RequestMethod.GET, 
			value="/detail/{projectName}/{mibFileName}/{id}", 
			produces={APPLICATION_JSON_VALUE})
	public Map<String, String> mibDetailForProjectAndMibFileName(@PathVariable String projectName, 
			@PathVariable String mibFileName, @PathVariable String id ){
		
		LOG.debug("Retrieve mib details for mib file name ({}) with id ({})", mibFileName, id);		
		Project project = projectService.getProjectByName(projectName);
		MibTree mibTree = project.retrieveMibTreeByFileName(mibFileName);
		if( null == mibTree){
			throw new MibTreeNotFoundException(projectName, mibFileName);
		}
		
		MibDetail mibDetail = mibTree.findMibDetail(id);
		if(null == mibDetail){
			throw new MibDetailNotFoundException(projectName, id); 
		}	
						
		return mibDetail.asMap();		
	}	

	@RequestMapping(method= RequestMethod.GET, 
			value="{projectName}/{mibFileName}", produces={APPLICATION_JSON_VALUE})
	public String mibTreeForProjectAndMibFileName(@PathVariable String projectName,
			@PathVariable String mibFileName){
		
		LOG.debug("Retrieve mib tree for project ({})", 
				projectName);
		
		Project project = projectService.getProjectByName(projectName);
		MibTree mibTree = project.retrieveMibTreeByFileName(mibFileName);
		if(null == mibTree){
			throw new MibTreeNotFoundException(projectName, mibFileName);
		}
	 
		String result = JSON.serialize(mibTree.getMibTreeObject()).replaceAll(" ", "");
		
		LOG.debug( result );
		
		return result;		
	}
	
	@RequestMapping(method= RequestMethod.GET, 
			value="{projectName}", produces={APPLICATION_JSON_VALUE})
	public List<MibTreeResource> allMibFileNamesForProject(@PathVariable String projectName){
		LOG.debug("Retrieve all the mib file names for project ({})", 
				projectName);
		
		Project project = projectService.getProjectByName(projectName);
		
		return project.getMibTrees().entrySet()
				.stream()
				.map(entry-> new MibTreeResource(entry.getKey(), entry.getValue().isPrimary()))
				.sorted()
				.collect(Collectors.toList());
	}
}
