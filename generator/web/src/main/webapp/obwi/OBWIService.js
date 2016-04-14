/**
 * 
 */
angular.module('cpgApp').factory('OBWISvc', function($http){
	'use strict';
	
	 var OBWIService = function(){ 
	 };
	 
	 OBWIService.prototype = {
	     retrieveOBWIMapping: function(url, callback){
	    	 $http.get(url).success(callback);
	     },
	     getOBWIMapping: function(){
	    	 return this.obwiMapping;
	     },
	     setOBWIMapping: function(obwiMapping){
	    	 this.obwiMapping = obwiMapping;
	     },
	     save: function(url, obwi, callback, error){
	    	 $http.put(url, obwi).success(callback).error(error);
	     }	     
	 };
	 
	 return new OBWIService();
});