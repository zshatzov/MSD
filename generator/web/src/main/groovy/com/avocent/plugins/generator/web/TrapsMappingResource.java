package com.avocent.plugins.generator.web;

import com.avocent.plugins.generator.model.ProjectStatus;
import com.avocent.plugins.generator.model.ProjectType;
import com.avocent.plugins.generator.model.common.TrapsInformation;


public class TrapsMappingResource extends LinkableResource{	

	private final TrapsInformation trapsMapping;
	
	public TrapsMappingResource(String name, ProjectType type, ProjectStatus status,
			final TrapsInformation trapsMapping) {
		super(name, type, status);
		this.trapsMapping = trapsMapping;
	}	

	public TrapsInformation getTrapsMapping() {
		return trapsMapping;
	}
}
