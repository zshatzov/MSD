package com.avocent.plugins.generator.web;

import java.util.List;

import com.avocent.plugins.generator.model.ProjectStatus;
import com.avocent.plugins.generator.model.ProjectType;
import com.avocent.plugins.generator.model.view.ContainerViewComponent;


public class DatapointMappingResource extends LinkableResource{ 	

	private final List<ContainerViewComponent> dataPointMapping;
	
	public DatapointMappingResource(String name, ProjectType type, ProjectStatus status,
			final List<ContainerViewComponent> datapointMapping) {
		super(name, type, status);
		this.dataPointMapping = datapointMapping;
	}	

	public List<ContainerViewComponent> getDataPointMapping() {
		return dataPointMapping;
	}
}
