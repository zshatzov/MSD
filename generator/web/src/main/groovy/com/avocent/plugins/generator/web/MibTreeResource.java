package com.avocent.plugins.generator.web;

import java.io.Serializable;

public class MibTreeResource implements Serializable, Comparable<MibTreeResource> {
	
	private final String mibFileName;
	private final boolean primary;	
		
	public MibTreeResource(String mibFileName, boolean primary) {
		super();
		this.mibFileName = mibFileName;
		this.primary = primary;
	}

	public String getMibFileName() {
		return mibFileName;
	}
	
	public boolean isPrimary() {
		return primary;
	}

	@Override
	public int compareTo(MibTreeResource other) {
		return this.getMibFileName().compareTo(other.mibFileName);
	}
}
