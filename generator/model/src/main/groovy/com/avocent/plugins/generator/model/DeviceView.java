package com.avocent.plugins.generator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.BasicDBList;

/**
 * <p>
 * A domain model that holds a particular <em>Device's</em>
 * MIB datapoint mappings. The mappings will be rendered by 
 * the client to allow a user to map a particular MIB file traps
 * to a pre-defined device view data point mapping field(s). 
 * </p>
 * 
 * @author zshatzov
 *
 */

@Document(collection="views")
public class DeviceView {
	
	@Id
	private String id;
	
	@Indexed(unique=true, name="project_type_unique_indx")
	private ProjectType projectType;
	
	private BasicDBList view;

	public String getId() {
		return id;
	} 

	public ProjectType getProjectType() {
		return projectType;
	} 

	public BasicDBList getView() {
		return view;
	} 

	public void setId(String id) {
		this.id = id;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public void setView(BasicDBList view) {
		this.view = view;
	}

	@Override
	public String toString() {
		return String.format("%s [projectType=%s]", getClass().getSimpleName(), 
				projectType);
	}  
}
