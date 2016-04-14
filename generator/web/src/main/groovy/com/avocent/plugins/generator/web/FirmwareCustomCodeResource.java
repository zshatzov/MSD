package com.avocent.plugins.generator.web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FirmwareCustomCodeResource implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7692396574476586671L;
	
	private final String fileName;
	private final Date lastModified;
	
	private transient static SimpleDateFormat df = 
			new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");	
	
	public FirmwareCustomCodeResource(String fileName, Date lastModified) {
		this.fileName = fileName;
		this.lastModified = lastModified;
	}

	public String getFileName() {
		return fileName;
	}

	public Date getLastModified() {
		return lastModified;
	}
	
	public String getLastModifiedFormatted(){
		return df.format(lastModified);
	}
}
