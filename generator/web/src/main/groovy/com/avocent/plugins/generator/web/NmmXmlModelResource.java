package com.avocent.plugins.generator.web;

import java.util.Date;

public class NmmXmlModelResource extends FileResource{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5439128102585769738L;
	
	private final String nmmApiType;

	public NmmXmlModelResource(String fileName, String 
				nmmApiType, Date uploadedDate) {
		super(fileName, uploadedDate);
		this.nmmApiType = nmmApiType; 
	}  

	public String getNmmApiType() {
		return nmmApiType;
	}
}
