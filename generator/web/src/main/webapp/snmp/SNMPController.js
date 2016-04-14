/**
 * 
 */
angular.module("cpgApp").controller('SNMPCtrl', function($scope, $window, $log, 
		SNMPSvc, projectLinksSvc){
	'use strict';
	
	SNMPSvc.retrieveSNMPMapping('/project/snmp/' + 
			$scope.activeProjectName, function(data){
		$scope.snmpMapping = data.snmpMapping;
		SNMPSvc.setSNMPMapping($scope.snmpMapping);
	});	
	
	$scope.save = function(){
		$log.debug("Save SNMP for project => " + $scope.activeProjectName);
		SNMPSvc.save(projectLinksSvc.getLinkByKey(
				$scope.activeProjectName, 'saveSNMP').value, 
				$scope.snmpMapping, function(data){
			$log.debug('Successfully saved SNMP data');
			$window.alert('SNMP successfully saved!');
		}, function(resp){
			$window.alert('Save SNMP failed with status: ' + resp.statusText);
		});
	};
});