package com.avocent.plugins.generator;

public class ProjectLoadResourceBundleException extends RuntimeException {

	private final String projectName;

	public ProjectLoadResourceBundleException(String projectName, Throwable cause) {
		super(cause);
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}
}
