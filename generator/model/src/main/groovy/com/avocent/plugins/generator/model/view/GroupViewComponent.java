package com.avocent.plugins.generator.model.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

/**
 * <p>
 * A POJO representing a grouping of {@link ElementViewComponent}
 * for a particular device view.
 * </p>
 * 
 * <p>
 * As an example a group might consolidate information about the
 * outlet information of a particular <em>PDU</em>
 * </p>
 * 
 * @author zshatzov
 *
 */
public class GroupViewComponent implements Serializable{
	
	private String group;
	private String label;

	@Valid
	private List<ElementViewComponent> elements = 
			new ArrayList<>();
	
	public String getGroup() {
		return group;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public void setElements(List<ElementViewComponent> elements) {
		this.elements = elements;
	}

	public void addElement(ElementViewComponent element){
		this.elements.add(element);
	}
	
	public List<ElementViewComponent> getElements() {
		return elements;
	}
}
