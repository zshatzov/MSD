package com.avocent.plugins.generator.web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileResource implements Serializable, Comparable<FileResource>{ 

	/**
	 * 
	 */
	private static final long serialVersionUID = -5965239467703958391L;

	private transient static SimpleDateFormat df = 
			new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");	

	private final String fileName;
	private final Date uploadedDate;

	public FileResource(String fileName, Date uploadedDate) {
		this.fileName = fileName;
		this.uploadedDate = uploadedDate;
	} 

	public String getFileName() {
		return fileName;
	}
	
	public Date getUploadedDate() {
		return uploadedDate;
	}
		
	public String getUploadedDateFormatted(){
		return df.format(uploadedDate);
	}

	@Override
	public String toString() {
		return String.format(
				"%s [fileName=%s, uploadedDate=%s]",
				getClass().getSimpleName(), fileName,	getUploadedDateFormatted());
	}

	@Override
	public int compareTo(FileResource other) {
		return this.fileName.compareTo(other.fileName);
	}
}
