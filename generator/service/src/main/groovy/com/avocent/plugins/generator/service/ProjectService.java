package com.avocent.plugins.generator.service;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.avocent.plugins.generator.DeleteProjectException;
import com.avocent.plugins.generator.ProjectAlreadyExistsException;
import com.avocent.plugins.generator.ProjectLoadResourceBundleException;
import com.avocent.plugins.generator.ProjectNotFoundException;
import com.avocent.plugins.generator.dao.ProjectRepository;
import com.avocent.plugins.generator.model.Project;
import com.avocent.plugins.generator.model.ProjectStatus;
import com.avocent.plugins.generator.model.ProjectType;
import com.avocent.plugins.generator.model.common.NMMXMLInformation;
import com.avocent.plugins.generator.model.common.OBWIConfiguration;
import com.avocent.plugins.generator.model.common.TrapsInformation;
import com.avocent.plugins.generator.model.view.ContainerViewComponent;
import com.avocent.plugins.generator.model.view.ElementViewComponent;

@Service
public class ProjectService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProjectService.class);

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ProjectPackagingService projectPackagingService;
		
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private Validator jsr303Validator;
	
	public void deleteMibTreeForProject(Project project, String mibFileName){
		if(project.deleteMibTree(mibFileName)){		
			projectPackagingService.deleteMibFile(project.getName(), mibFileName);
			projectRepository.save(project);
		}
	}
	
	public void deleteProject(String projectName) throws DeleteProjectException{
		
		Project candidate = projectRepository.findByName(projectName);		
		if( null == candidate || 
			candidate.getStatus() != ProjectStatus.PARTIAL) return;
		
		try{			
			projectPackagingService.deleteProjectStructure(projectName);
			projectRepository.delete(candidate);
		}catch(Exception e){
			LOG.error("Failed to delete project directory", e);
			throw new DeleteProjectException(projectName, e);
		}
	}
	
	public List<Project> getAllProjects(){
		return projectRepository.findAll();
	}
	
	public List<Project> getAllProjectsByStatus(ProjectStatus status){
		return projectRepository.findByStatus(status);
	}
	
	public List<Project> getAllProjectsByType(ProjectType type){
		return projectRepository.findByType(type);
	}
	
	public Project getProjectByName(String projectName) throws ProjectNotFoundException{
		Project project = projectRepository.findByName(projectName); 
		if(null == project){
			throw new ProjectNotFoundException(projectName);
		}
		
		return project;
	}
	
	public URLClassLoader getProjectResourceBundleClassloader(String projectName, Path path) throws ProjectLoadResourceBundleException{
		try{
			return URLClassLoader.newInstance(
					new URL[]{path.toUri().toURL()}, 
					ClassLoader.getSystemClassLoader()) ;
			
		}catch(Exception e){
			throw new ProjectLoadResourceBundleException(projectName, e);
		}
	}
	
	public String previewProject(Project project) throws IOException{
		Path xml = projectPackagingService.getProjectGeneratedXmlFilePath(project);
		return Files.lines(xml)
		     .collect(Collectors.joining("\n"));
	}
	
	public Project saveProject(Project project) throws ProjectAlreadyExistsException{
		Project result = null;
		try{
			result =  projectRepository.save(project);
		}catch(DuplicateKeyException e){			
			throw new ProjectAlreadyExistsException(project.getName());
		}
		
		return result;
	}
	
	public boolean validateProjectWithNameNotExists(String projectName){
		Criteria projectNameExists =  Criteria.where("name").is(projectName);
		Query query =  new Query(projectNameExists);
		
		return mongoTemplate.count(query, Project.class) < 1;		 
	}
	
	public Set<ConstraintViolation<ElementViewComponent>> validateDatapoints( 
			List<ContainerViewComponent> datapoints){
		final Set<ConstraintViolation<ElementViewComponent>> result = new HashSet<>();
		
		datapoints.stream()
		.flatMap(container -> container.getGroups().stream())				
		.flatMap(group-> group.getElements().stream())
		.forEach(element-> {					
			Set<ConstraintViolation<ElementViewComponent>> validationResults = jsr303Validator
					.validate(element);
			if (validationResults.size() > 0) {
				result.addAll(validationResults);
			}
		});
		
		return result;		
	}
	
	public Set<ConstraintViolation<NMMXMLInformation>> validateNMM( 
			NMMXMLInformation nmm){		
		 return jsr303Validator.validate(nmm);		
	}
	
	public Set<ConstraintViolation<OBWIConfiguration>> validateOBWI( 
			OBWIConfiguration obwi){		
		 return jsr303Validator.validate(obwi);		
	}
	
	public Set<ConstraintViolation<TrapsInformation>> validateTraps( 
			TrapsInformation traps){		
		 return jsr303Validator.validate(traps);		
	} 
	
	public void verifyProjectExists(String projectName) throws ProjectNotFoundException{		
		Criteria projectNameExists =  Criteria.where("name").is(projectName);
		Query query =  new Query(projectNameExists);
		Project project = mongoTemplate.findOne(query, Project.class);
		
		if(null == project){
			throw new ProjectNotFoundException(projectName);
		}
	}
}
