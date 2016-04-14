/**
 * 
 */

angular.module('cpgApp').factory('CustomCodeModel', function(){
	'use strict';
	
	var FirmwareUpgrade = function(){
		this.invokeClassName = '';
		this.invokeMethodName = '';
	};
	
	var CustomCodeInformation = function(){
		this.customCodeInformation = {};
		this.customCodeInformation.firmwareUpgrade = new FirmwareUpgrade();
	};	 
	
	return CustomCodeInformation;	
});


angular.module('cpgApp').factory('customCodeSvc', function($http){
	'use strict';
	
	var CustomCodeService = function(){		
	};
	
	CustomCodeService.prototype = {
		getUploadedFirmware: function(url, callback){
			$http.get(url).success(callback);
		},
		retrieveCustomCodeMapping: function(url, callback){
			$http.get(url).success(callback);
		},
		getCustomCodeMapping: function(){
			return this.customCodeInformation;
		},
		setCustomCodeMapping: function(customCode){
			this.customCodeInformation = customCode;
		},
		deleteCustomCode: function(url){
			$http.delete(url);
		},
		save: function(url, customCode, callback, error){
			$http.put(url, customCode).success(callback).error(error);
		}
	};
	
	return new CustomCodeService();
});