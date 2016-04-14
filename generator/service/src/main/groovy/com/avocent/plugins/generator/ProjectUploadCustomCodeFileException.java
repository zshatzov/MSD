package com.avocent.plugins.generator;

public class ProjectUploadCustomCodeFileException extends RuntimeException {
	
	private String projectName;

	public ProjectUploadCustomCodeFileException(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}
}
