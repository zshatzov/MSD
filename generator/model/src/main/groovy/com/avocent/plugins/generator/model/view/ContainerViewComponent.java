package com.avocent.plugins.generator.model.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

public class ContainerViewComponent implements Serializable{
	private String name;
	private String label;
	
	@Valid
	private List<GroupViewComponent> groups = 
			new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public List<GroupViewComponent> getGroups() {
		return groups;
	}

	public void addGroup(GroupViewComponent group){
		this.groups.add(group);
	}

	public void setGroups(List<GroupViewComponent> groups) {
		this.groups = groups;
	}	
}
