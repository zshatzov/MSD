package com.avocent.plugins.generator.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * A domain object that holds extra information for any given {@link MibNode}
 * instance. The method {@link MibDetail#asMap()} returns an instance of
 * {@link java.util.Map} that represents a view of the instance attributes
 * that were populated with a value. That is if an attribute has no value
 * it will not be present in the resulting {@link java.util.Map}.
 * </p>
 * 
 * @author zshatzov
 *
 */

public class MibDetail implements Serializable{
	/**
	 * 
	 */	
	private static final long serialVersionUID = -1938454302662443087L;
	
	private String oid;
	
	private String access;
	private boolean accessPopulated = false;
	
	private String status;
	private boolean statusPopulated = false;
	
	private String description;
	private boolean descriptionPopulated = false;
	
	private String lastUpdated;
	private boolean lastUpdatedPopulated = false;
	
	private String organization;
	private boolean organizationPopulated = false;
	
	private String syntax;
	private boolean syntaxPopulated = false;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getAccess() {
		return access;
	}
	
	public void setAccess(String access) {
		this.access = access;
		this.accessPopulated = true;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
		this.statusPopulated = true;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
		this.descriptionPopulated = true;
	}	

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
		this.lastUpdatedPopulated = true;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
		this.organizationPopulated = true;		
	}
	
	public String getSyntax() {
		return syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
		this.syntaxPopulated = true;
	}

	public Map<String, String> asMap(){	
		
		Map<String, String> details = new LinkedHashMap<>();
		
		if(this.organizationPopulated){
			details.put("organization", organization);
		}
		
		if(this.lastUpdatedPopulated){
			details.put("lastUpdated", lastUpdated);
		}
		
		if(this.statusPopulated){
			details.put("status", status);
		}
		
		if(this.accessPopulated){
			details.put("access", access);
		}
		
		if(this.descriptionPopulated){
			details.put("description", description);
		}
		
		if(this.syntaxPopulated){
			details.put("syntax", syntax);
		}
		
		return details;
	}

	public boolean detailExists() {		
		return statusPopulated || 
			   accessPopulated || 
			   descriptionPopulated	|| 
			   organizationPopulated || 
			   lastUpdatedPopulated ||
			   syntaxPopulated;
	}	
	
	@Override
	public String toString() {
		return String.format(
				"%s [oid = %s, access=%s, status=%s, description=%s]", getClass().getSimpleName(), 
				oid, access, status, description);
	}
}
