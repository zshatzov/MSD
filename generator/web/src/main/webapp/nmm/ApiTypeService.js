/**
 * 
 */
angular.module('cpgApp').factory('nmmApiTypeSvc', function($http){
	'use strict';
	
	var NMMApiTypeService = function(){
	};
	
	NMMApiTypeService.prototype = {		
		nmmXmlFileUploadedForProject: function(url, callback){	
			$http.get(url).success(callback);
		}
	};
	
	return new NMMApiTypeService();
});