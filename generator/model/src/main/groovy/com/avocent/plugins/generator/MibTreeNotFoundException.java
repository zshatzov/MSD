package com.avocent.plugins.generator;

public class MibTreeNotFoundException extends RuntimeException {
	
	private String projectName;
	private String mibFileName;
	
	public MibTreeNotFoundException(String projectName, String mibFileName) {
		super();
		this.projectName = projectName;
		this.mibFileName = mibFileName;
	}

	public String getProjectName() {
		return projectName;
	}
	
	public String getMibFileName() {
		return mibFileName;
	}
}
