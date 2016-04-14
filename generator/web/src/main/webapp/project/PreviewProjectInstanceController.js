angular.module('cpgApp').controller('PreviewProjectInstanceCtrl', function ($scope, $http, 
		$modalInstance, $window, projectSvc, url) {
	'use strict';
		 
	projectSvc.previewProject(url, function(data){	
		 $scope.dataXML = data;
	},function(resp){
		$window.alert("Preview project failed with status: " + resp.statusText);
	});	 
	
	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	};
});