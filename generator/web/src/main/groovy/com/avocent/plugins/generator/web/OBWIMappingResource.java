package com.avocent.plugins.generator.web;

import com.avocent.plugins.generator.model.ProjectStatus;
import com.avocent.plugins.generator.model.ProjectType;
import com.avocent.plugins.generator.model.common.OBWIConfiguration;


public class OBWIMappingResource extends LinkableResource{
	
	private final OBWIConfiguration obwiMapping;
	
	public OBWIMappingResource(String name, ProjectType type, ProjectStatus status,
			final OBWIConfiguration obwiMapping) {
		super(name, type, status);
		this.obwiMapping = obwiMapping;
	}	

	public OBWIConfiguration getObwiMapping() {
		return obwiMapping;
	}
}
