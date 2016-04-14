/**
 * 
 */
angular.module("cpgApp").factory('projectSvc', function($http, Upload){
	'use strict';
	
	var Project = function(){
		this.name = '';
		this.type = '';
	};
	
	Project.retrieveAllPartialProjects = function(url, callback){
		$http.get(url).success(callback);
	}; 
	
	Project.retrieveAllGeneratedProjects = function(url, callback){
		$http.get(url).success(callback);
	}; 
	
	Project.retrieveAllCompleteProjects = function(url, callback){
		$http.get(url).success(callback);
	};
	
	Project.openProject = function(url, callback, error){
		$http.get(url).success(callback).error(error);
	};
	
	Project.deleteProject = function(url, callback, error){
		$http.delete(url).success(callback).error(error);
	};
	
	Project.retrieveGeneratedPlugins = function(url, callback){
		$http.get(url).success(callback);
	};
	
	Project.deletePlugin = function(url, callback, error){
		$http.delete(url).success(callback).error(error);
	};
	
	Project.previewProject = function(url, callback, error){
		$http.get(url).success(callback).error(error);
	};
	
	Project.generateProject = function(url, callback, error){
		$http.put(url).success(callback).error(error);
	};
	
	Project.prototype = {
		getProjectName: function(){
			return this.name;
		},
		setProjectName: function(name){
			this.name = name;
		},
		getProjectType: function(){
			return this.type;
		},
		setProjectType: function(type){
			this.type = type;
		},
		retrieveViewTypes: function(url, callback){
			$http.get(url).success(callback);
		},
		saveProject: function(url, project, file, success, error){
		   Upload.upload({
		       url: url,
		       method: 'POST',
		       data: {mibFile: file, name: project.name, type: project.type}
		   }).then(success, error);
		},
		saveProjectWithMapping: function(url, project, mibFile, mappingFile, success, error){
		   Upload.upload({
		       url: url,
		       method: 'POST',
		       data: {mibFile: mibFile, mappingFile: mappingFile, name: project.name}
		   }).then(success, error);
		}
	};
	
	return Project;
});
