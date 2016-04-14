package com.avocent.plugins.generator.web;

import com.avocent.plugins.generator.model.ProjectStatus;
import com.avocent.plugins.generator.model.ProjectType;
import com.avocent.plugins.generator.model.common.CustomCodeInformation;

public class CustomCodeResource extends LinkableResource {
	
	private final CustomCodeInformation customCodeInformation;

	public CustomCodeResource(String name, ProjectType type,
			ProjectStatus status, CustomCodeInformation customCode) {
		super(name, type, status);
		this.customCodeInformation = customCode;
	}

	public CustomCodeInformation getCustomCodeInformation() {
		return customCodeInformation;
	}
}
