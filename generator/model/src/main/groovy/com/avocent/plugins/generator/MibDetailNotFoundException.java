package com.avocent.plugins.generator;

public class MibDetailNotFoundException extends RuntimeException {
	
	private final String projectName;
	private final String mibOid;
	
	public MibDetailNotFoundException(String projectName, String mibOid) {
		super();
		this.projectName = projectName;
		this.mibOid = mibOid;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getMibOid() {
		return mibOid;
	}
}
