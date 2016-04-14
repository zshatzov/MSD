package com.avocent.plugins.generator.web;

import java.io.Serializable;

import com.avocent.plugins.generator.model.Project;
import com.avocent.plugins.generator.model.ProjectStatus;
import com.avocent.plugins.generator.model.ProjectType;

/**
 * 
 * <p>A base class that represents a resource
 * mapping to a particular {@link Project}, the mapping
 * identifies the project by name, the status of the project
 * and the type of the project.
 * </p>
 * 
 * @author zshatzov
 *
 */
public abstract class MappableResource implements Serializable{

	protected final String name; 
	protected final ProjectType type;
	protected final ProjectStatus status;	
	
	protected MappableResource(String name, ProjectType type,
			ProjectStatus status) {
		super();
		this.name = name;
		this.type = type;
		this.status = status;
	}

	public String getName() {
		return name;
	}
	
	public ProjectType getType() {
		return type;
	}
	
	public ProjectStatus getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return String.format(
				"%s [projectName=%s]",	getClass().getSimpleName(), name);
	}	
}
