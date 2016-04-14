package com.avocent.plugins.generator;

public class ProjectCreateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1748964407470428948L;
	
	private String projectName;

	public ProjectCreateException(String projectName) {
		super();
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
