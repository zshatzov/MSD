package com.avocent.plugins.generator.model;

/**
 * <p>
 * Enum to represents different states in the plugins project
 * lifecycle. The {@link com.avocent.plugins.generator.model.Project} object
 * can be in any one of the listed states. A {@link ProjectStatus.PARTIAL}
 * state represents a project that has been modified but not completed yet.
 * </p>
 * 
 * @author zshatzov
 *
 */
public enum ProjectStatus {
	PARTIAL, COMPLETE, GENERATED
}
