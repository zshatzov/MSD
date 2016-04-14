package com.avocent.plugins.generator;

public class PluginGenerationException extends RuntimeException{

	private final String projectName;
	
	public PluginGenerationException(String projectName, Throwable cause) {
		 super(cause);
		 this.projectName = projectName;		
	}
	
	public String getProjectName() {
		return projectName;
	}	 
}
