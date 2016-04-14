package com.avocent.plugins.generator;

public class ProjectUploadCommonDataFileException extends RuntimeException {
	
	private String projectName;

	public ProjectUploadCommonDataFileException(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}
}
