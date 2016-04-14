package com.avocent.plugins.generator;

public class ProjectFileDownloadException extends RuntimeException {
	
	private final String projectName;

	public ProjectFileDownloadException(String projectName, Throwable cause) {
		super(cause);
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}
}
