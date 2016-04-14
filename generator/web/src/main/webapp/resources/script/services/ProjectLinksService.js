/**
 * 
 */

angular.module('cpgApp').factory('projectLinksSvc', function($http){
	'use strict';
	
	var ProjectLinksService = function(){
		this.cache = {};		
	};
	
	ProjectLinksService.prototype = {
		cacheLinks: function(projectName, links){
			var self = this;
			if(!self.cache.hasOwnProperty(projectName)){
			  self.cache[projectName] = {};
			  self.cache[projectName].links = []; 
			  _.each(links, function(link){				
				   self.cache[projectName].links.push(link);			
			  });
			}
		},
		getLinkByKey: function(projectName, key){
			if(this.cache.hasOwnProperty(projectName)){
				var result =  _.find(this.cache[projectName].links, function(link){
					 return link.key === key
				});
				
				return result;
			}else{
				return null;
			}
		},
		getLinksForProject: function(projectName){
			return this.cache[projectName];
		}
	};
	
	return new ProjectLinksService();
});