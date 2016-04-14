package com.avocent.plugins.generator;

public class DeleteProjectException extends RuntimeException { 
	
	private String projectName;
 
	public DeleteProjectException(String projectName, Throwable t) {
		super(t);
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}	
	
	public String getMessage(){
		return super.getCause().getMessage();
	}

	@Override
	public String toString() {
		return String.format("%s [projectName=%s]",
				getClass().getSimpleName(), projectName);
	}
}
