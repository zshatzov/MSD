package com.avocent.plugins.generator.web;

import com.avocent.plugins.generator.model.ProjectStatus;
import com.avocent.plugins.generator.model.ProjectType;
import com.avocent.plugins.generator.model.common.NMMXMLInformation;


public class NMMMappingResource extends LinkableResource{ 	

	private final NMMXMLInformation nmmMapping;
	
	public NMMMappingResource(String name, ProjectType type, ProjectStatus status,
			final NMMXMLInformation nmmMapping) {
		super(name, type, status);
		this.nmmMapping = nmmMapping;
	}	

	public NMMXMLInformation getNmmMapping() {
		return nmmMapping;
	}
}
