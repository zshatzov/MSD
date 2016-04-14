/**
 * 
 */

angular.module('cpgApp').factory('datapointSvc', function($http){
	'use strict';
	
	var DatapointService = function(){		
	};
	
	DatapointService.prototype = {
		retrieveDatapoints: function(url, success, error){
			$http.get(url).success(success).error(error);
		},
		getDatapointMapping: function(){
			return this.datapointMapping;
		},
		setDatapointMapping: function(datapointMapping){
			this.datapointMapping = datapointMapping;
		},
		save: function(url, datapoints, callback, error){
			$http.put(url, datapoints).success(callback).error(error);
		}
	};
	
	return new DatapointService();
});