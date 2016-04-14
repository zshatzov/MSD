/**
 * 
 */
angular.module("cpgApp").controller("InProgressProjectCtrl", 
  function($scope, $controller, projectSvc, projectLinksSvc){
	'use strict';
	
	$controller('ProjectCtrl', {$scope: $scope});
	  
	projectSvc.retrieveAllPartialProjects('/project/status/PARTIAL', function(data){
		$scope.partialProjects = [].concat(data);
		$scope.rowCollection = [].concat($scope.partialProjects);
	});
});