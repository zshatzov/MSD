/**
 * 
 */

angular.module("cpgApp").controller('DatapointViewCtrl', function($scope, $window, datapointSvc){
	'use strict';
	
	datapointSvc.retrieveDatapoints('/project/datapoints/' + $scope.activeProjectName, function(data){
		$scope.datapoints = data.dataPointMapping;
		datapointSvc.setDatapointMapping($scope.datapoints);
	}, function(error){
		$window.alert(error.message);
	});	
});