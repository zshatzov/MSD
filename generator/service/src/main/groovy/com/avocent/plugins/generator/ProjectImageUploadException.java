package com.avocent.plugins.generator;

public class ProjectImageUploadException extends RuntimeException {

	private final String projectName;
	
	public ProjectImageUploadException(String projectName, Throwable cause) {
		super(cause);
		this.projectName =  projectName;
	}

	public String getProjectName() {
		return projectName;
	}
}
