package com.avocent.plugins.generator;

public class ProjectAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7826635592778067500L;
	private String projectName;

	public ProjectAlreadyExistsException(String projectName) {
		super();
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String toString() {
		return String.format("%s [projectName=%s]",
				getClass().getSimpleName(), projectName);
	} 
}
