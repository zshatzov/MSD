package com.avocent.plugins.generator.web;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.avocent.plugins.generator.model.Project;
import com.avocent.plugins.generator.model.ProjectStatus;
import com.avocent.plugins.generator.model.ProjectType;
import com.avocent.plugins.generator.service.LinkEntry;
/**
 * <p>
 * This class provides the capability for a resource to expose
 * additional resources provided by the specific {@link Project}.
 * </p>
 * 
 * 
 * @author zshatzov
 *
 */
public abstract class LinkableResource  extends MappableResource{	
 
	protected LinkableResource(String name, ProjectType type, ProjectStatus status) {
		super(name, type, status);
	}

	protected Set<LinkEntry> links = new HashSet<>();	
	
	public LinkableResource addLink(String key, URI contextPath, String... paths){			
		String path = Stream.of(paths).collect(Collectors.joining("/"));			
		String url = contextPath.toString() + path;			
		links.add(new LinkEntry(key, url));
		return this;
	}

	public void setLinks(Set<LinkEntry> links){
		this.links = new HashSet<>(links);
	}
	
	public Set<LinkEntry> getLinks() {
		return links;
	}
}
