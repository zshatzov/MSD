package com.avocent.plugins.generator.model.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * A POJO representing extra information about MIB attribute mapping
 * specifically used for UI rendering.  
 * </p>  
 * 
 * @author zshatzov
 *
 */
public class MetadataViewComponent implements Serializable{

	private boolean required;
	
	private List<String> enumeration = new ArrayList<>();

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}
	
	public void addEnumeration(String enumeration){
		this.enumeration.add(enumeration);
	}

	public List<String> getEnumeration() {
		return enumeration;
	}

	public void setEnumeration(List<String> enumeration) {
		this.enumeration = enumeration;
	}
}
