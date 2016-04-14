package com.avocent.plugins.generator.web;

import com.avocent.plugins.generator.model.ProjectStatus;
import com.avocent.plugins.generator.model.ProjectType;
import com.avocent.plugins.generator.model.common.SNMPConfiguration;


public class SNMPMappingResource extends LinkableResource{ 	
	
	private final SNMPConfiguration snmpMapping;
	
	public SNMPMappingResource(String name, ProjectType type, ProjectStatus status,
			final SNMPConfiguration snmpMapping) {
		super(name, type, status);
		this.snmpMapping = snmpMapping;
	}	

	public SNMPConfiguration getSnmpMapping() {
		return snmpMapping;
	}
}
