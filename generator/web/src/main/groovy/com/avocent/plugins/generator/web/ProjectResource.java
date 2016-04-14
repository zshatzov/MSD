package com.avocent.plugins.generator.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.avocent.plugins.generator.model.ProjectStatus;
import com.avocent.plugins.generator.model.ProjectType;

public class ProjectResource extends LinkableResource{	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -6172672138903009978L;
		
	private transient static SimpleDateFormat df = 
			new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");	
	
	private final Date lastModified;
	private final Date createdDate;

	public ProjectResource(String name, ProjectType type, 
			ProjectStatus status, Date cratedDate, Date lastModified) {
		super(name, type, status);
		this.createdDate = cratedDate;
		this.lastModified = lastModified;
	} 
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public String getCreatedDateFormatted() {
		return df.format(createdDate);
	}
	
	public Date getLastModified() {
		return lastModified;
	}
	
	public String getLastModifiedFormatted(){
		return df.format(lastModified);
	}

	@Override
	public String toString() {
		return String.format(
				"%s [projectName=%s, projectType=%s, projectStatus=%s]",
				getClass().getSimpleName(), name, type, status);
	}
}
