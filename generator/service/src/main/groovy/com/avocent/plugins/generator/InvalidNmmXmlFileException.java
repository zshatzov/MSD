package com.avocent.plugins.generator;

public class InvalidNmmXmlFileException extends RuntimeException{
	
	private final String projectName;
	private final String nmmXmlFileName;
  
	public InvalidNmmXmlFileException(String projectName, String nmmXmlFileName) {
		super();
		this.projectName = projectName;
		this.nmmXmlFileName = nmmXmlFileName;
	}

	public String getNmmXmlFileName() {
		return nmmXmlFileName;
	} 

	public String getProjectName() {
		return projectName;
	} 
}
