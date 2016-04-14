package com.avocent.plugins.generator;

public class ProjectNotFoundException extends RuntimeException { 
	
	private String projectName;

	public ProjectNotFoundException(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}

	@Override
	public String toString() {
		return String.format("%s [projectName=%s]",
				getClass().getSimpleName(), projectName);
	}
}
