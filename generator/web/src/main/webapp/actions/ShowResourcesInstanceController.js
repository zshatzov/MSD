/**
 * 
 */
angular.module('cpgApp').controller('ShowResourcesInstanceCtrl', function ($scope, $http,
		$modalInstance, projectLinksSvc, projectName) {
	'use strict';

	var controller = this;
 
	$http.get(projectLinksSvc.getLinkByKey(
			projectName, 'projectResources').value).success(function(data){
		$scope.helpFiles = [].concat(data.help);
		$scope.images = [].concat(data.images);
		$scope.bundles = [].concat(data.resourceBundles);
		$scope.customCode = [].concat(data.customCode);
	});
   
	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	};
});