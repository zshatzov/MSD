package com.avocent.plugins.generator.web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PluginArtifactResource implements Serializable, Comparable<PluginArtifactResource>{	 		
	/**
	 * 
	 */
	private static final long serialVersionUID = 9181535916599513999L;

	private transient static SimpleDateFormat df = 
			new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");	
	
	private final String pluginName;
	private final String pluginVersion;
	private final Date lastModified;

	public PluginArtifactResource(String pluginName, String 
				pluginVersion, Date lastModified) {
		this.pluginName = pluginName;
		this.pluginVersion = pluginVersion;
		this.lastModified = lastModified;
	} 
	
	public String getPluginName() {
		return pluginName;
	}

	public String getPluginVersion() {
		return pluginVersion;
	}

	public Date getLastModified() {
		return lastModified;
	}
	
	public String getLastModifiedFormatted(){
		return df.format(lastModified);
	}
	
	public String getFileName(){
		return String.join("_", getPluginName(), getPluginVersion());
	}

	@Override
	public String toString() {
		return String.format(
				"%s [pluginName=%s, pluginVersion=%s, lastModified=%s]",
				getClass().getSimpleName(), pluginName, 
				pluginVersion, getLastModifiedFormatted());
	}

	@Override
	public int compareTo(PluginArtifactResource other) {
		return this.lastModified.compareTo(other.lastModified);
	}
}
