/**
 * 
 */
angular.module('cpgApp').factory('SNMPSvc', function($http){
	'use strict';
	
	 var SNMPService = function(){ 
	 };
	 
	 SNMPService.prototype = {
	     retrieveSNMPMapping: function(url, callback){
	    	 $http.get(url).success(callback);
	     },
	     getSNMPMapping: function(){
	    	 return this.snmpMapping;
	     },
	     setSNMPMapping: function(snmpMapping){
	    	 this.snmpMapping = snmpMapping;
	     },
		 save: function(url, snmp, callback, error){
			$http.put(url, snmp).success(callback).error(error);
		 }	     
	 };
	 
	 return new SNMPService();
});