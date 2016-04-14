package com.avocent.plugins.generator;

public class ProjectHelpFileUploadException extends RuntimeException {

	private final String projectName;
	
	public ProjectHelpFileUploadException(String projectName, Throwable cause) {
		super(cause);
		this.projectName =  projectName;
	}

	public String getProjectName() {
		return projectName;
	}
}
