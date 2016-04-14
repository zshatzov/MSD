/**
 * 
 */
angular.module('cpgApp').factory('resourceBundleSvc', function($http){
	'use strict';
	
	var ResourceBundleService = function(){ 
	};
	
	ResourceBundleService.prototype = {
		bundlesUploadedForProject: function(url, callback){	
			$http.get(url).success(callback);
		}
	};
	
	return new ResourceBundleService();
});