package com.avocent.plugins.generator;

public class ProjectResourceBundleUploadException extends RuntimeException {

	private final String projectName;
	
	public ProjectResourceBundleUploadException(String projectName, Throwable cause) {
		super(cause);
		this.projectName =  projectName;
	}

	public String getProjectName() {
		return projectName;
	}
}
