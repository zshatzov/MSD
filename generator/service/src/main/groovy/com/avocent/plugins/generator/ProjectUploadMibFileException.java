package com.avocent.plugins.generator;

public class ProjectUploadMibFileException extends RuntimeException {
	
	private String projectName;

	public ProjectUploadMibFileException(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}
}
