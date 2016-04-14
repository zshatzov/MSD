/**
 * 
 */
angular.module('cpgApp').factory('mibSvc', function($http){
	'use strict';
	
	var MibService = function(){		
	};
	
	MibService.prototype = {
		getPrimaryMib: function(url, callback){
			$http.get(url).success(callback); 
		},
		getTrapsInfoFromPrimaryMib: function(url, callback){
			this.getPrimaryMib(url, function(data){
				return {
					pluginName: data.children[0].text,
					enterpriseOID: data.children[0].id,
					trapMIBBase: data.children[0].id
				};
			});
		},
		getMibFileNames: function(url, callback){
			$http.get(url).success(callback);
		},
		getMibTreeByFileName: function(url, callback){
			$http.get(url).success(callback);
		},
		getDetailsForMibNode: function(url, success, error){
			$http.get(url, {cache: true}).success(success).error(error);
		}
	};
	
	return new MibService();
});