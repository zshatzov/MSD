package com.avocent.plugins.generator.web.aop;

import javax.validation.Validator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avocent.plugins.generator.model.Project;
import com.avocent.plugins.generator.model.ProjectStatus;
import com.avocent.plugins.generator.service.ProjectService;
import com.avocent.plugins.generator.service.XMLFileGeneratorService;

@Aspect
@Component
public class SaveMappingAspect {

	private static final Logger LOG = LoggerFactory
			.getLogger(SaveMappingAspect.class);

	@Autowired
	private ProjectService projectService;

	@Autowired
	private XMLFileGeneratorService fileGenerator;

	@Autowired
	private Validator jsr303Validator;

	@After("execution(void com.avocent.plugins.generator.web.controller.ProjectRestController.save(..))")
	public void handleSaveMappings(JoinPoint jp) {

		String projectName = (String) jp.getArgs()[0];

		Project project = projectService.getProjectByName(projectName);

		LOG.debug("Validating mappings for project ({})", projectName);
		if (!validMappings(project)){
			LOG.info("Project ({}) is incomplete skipping XML file generation",
					projectName);
			if(project.getStatus() != ProjectStatus.PARTIAL){
				// If a project being edited is not in PARTIAL state and it fails validation,
				// change it's status to PARTIAL!
				project.setStatus(ProjectStatus.PARTIAL);		
				projectService.saveProject(project);
			}
			return;
		}

		if (fileGenerator.generateFile(project)) {
			project.setStatus(ProjectStatus.COMPLETE);
			LOG.info("Generated XML File mapping for project ({})", projectName);
		} else {
			project.setStatus(ProjectStatus.PARTIAL);
			LOG.info("Project ({}) is incomplete", projectName);
		}

		LOG.debug("Save changes to project ({0})", project.getName());
		projectService.saveProject(project);
	}
	
	private boolean validMappings(Project project){		
		return projectService.validateDatapoints(project.getDataPointMapping()).size() ==  0 &&
				projectService.validateNMM(project.getNmmMapping()).size() == 0 &&
				projectService.validateTraps(project.getTrapsMapping()).size() == 0 &&
				projectService.validateOBWI(project.getObwiMapping()).size() == 0;
	}
}
