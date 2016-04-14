package com.avocent.plugins.generator.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.avocent.plugins.generator.model.DeviceView;
import com.avocent.plugins.generator.model.ProjectType;

public interface DeviceViewRepository extends MongoRepository<DeviceView, String> {

	DeviceView findByProjectType(ProjectType projectType);
	
}
