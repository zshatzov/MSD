package com.avocent.plugins.generator.model.view;

import java.io.Serializable;

import com.avocent.plugins.generator.model.validator.ViewComponentRequired;

/**
 * <p>
 * A POJO representing a specific MIB attribute mapping.
 * </p>
 * 
 * @author zshatzov
 *
 */
@ViewComponentRequired
public class ElementViewComponent implements Serializable{

	private String name;
	private String label;
	private String value;
	private MetadataViewComponent metadata;
	
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
			
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public MetadataViewComponent getMetadata() {
		return metadata;
	}
	
	public void setMetadata(MetadataViewComponent metadata) {
		this.metadata = metadata;
	}
}
