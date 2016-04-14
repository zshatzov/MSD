package com.avocent.plugins.generator;

public class ProjectUploadNmmXmlFileException extends RuntimeException {
	
	private String projectName;

	public ProjectUploadNmmXmlFileException(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}
}
