angular.module('cpgApp').factory('serverValidationSvc', function($http, $log){
	'use strict';
	
	var ServerValidationService = function(){};
	ServerValidationService.prototype = {
		validate: function(url, callback){
			$http.get(url).success(function(resp){	
				$log.debug("Validation response => " + resp); 
				callback(resp);
			});
		},
		getGroupNameFromLabel: function(url, label, callback){
			$http.get(url).success(function(resp){
				$log.debug("Convert label (" + label + ") => " + resp[label]);
				callback(resp[label]);
			});
		}
	};
	
	return new ServerValidationService();
});