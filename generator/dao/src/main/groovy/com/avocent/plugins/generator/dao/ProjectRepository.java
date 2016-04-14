package com.avocent.plugins.generator.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.avocent.plugins.generator.model.Project;
import com.avocent.plugins.generator.model.ProjectStatus;
import com.avocent.plugins.generator.model.ProjectType;

public interface ProjectRepository extends MongoRepository<Project, String> {
	
	Project findByName(String name);
	
	List<Project> findByStatus(ProjectStatus status);
	
	List<Project> findByType(ProjectType type);
	
	void delete(Project entity);
}
