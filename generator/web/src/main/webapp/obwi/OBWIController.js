/**
 * 
 */
angular.module("cpgApp").controller('OBWICtrl', function($scope, $window, 
		$log, OBWISvc, projectLinksSvc){
	'use strict';
	
	OBWISvc.retrieveOBWIMapping('/project/obwi/' + $scope.activeProjectName, function(data){
		$scope.obwiMapping = data.obwiMapping;
		OBWISvc.setOBWIMapping($scope.obwiMapping);
	});		
	
	$scope.save = function(){
		$log.debug("Save OBWI for project => " + $scope.activeProjectName);
		OBWISvc.save(projectLinksSvc.getLinkByKey(
				$scope.activeProjectName, 'saveOBWI').value, 
				$scope.obwiMapping, function(data){
			$log.debug('Successfully saved OBWI data');
			$window.alert('OBWI successfully saved!');
		}, function(resp){
			$window.alert('Save OBWI failed with status: ' + resp.statusText);
		});
	};
});