/**
 * 
 */
angular.module("cpgApp").controller('ActionsCtrl', function($scope, $window, $modal){
	'use strict';
	
	$scope.showResources = function() {		
		var modalInstance = $modal.open({
			templateUrl : '/actions/showResourcesModal.html',
			controller : 'ShowResourcesInstanceCtrl',
			size : 'lg',
			resolve: {
				projectName: function(){
					return $scope.activeProjectName;
				}
			}
		});
	};
	
	$scope.manageMIB = function() {		
		var modalInstance = $modal.open({
			templateUrl : '/actions/manageMIBModal.html',
			controller : 'ManageMIBInstanceCtrl',
			size : 'lg',
			resolve: {
				projectName: function(){
					return $scope.activeProjectName;
				}
			}			
		});
	};	
	
	$scope.uploadResourceBundle = function() {		
		var modalInstance = $modal.open({
			templateUrl : '/actions/resourceBundleModal.html',
			controller : 'UploadResourceBundleInstanceCtrl',
			size : 'sm',
			resolve: {
				projectName: function(){
					return $scope.activeProjectName;
				}
			}			
		});
	};	
	
	$scope.uploadHelpFile = function() {		
		var modalInstance = $modal.open({
			templateUrl : '/actions/helpFileModal.html',
			controller : 'UploadHelpFileInstanceCtrl',
			size : 'sm',
			resolve: {
				projectName: function(){
					return $scope.activeProjectName;
				}
			}			
		});
	};	
	
	
	$scope.manageNMMXML = function() {		
		var modalInstance = $modal.open({
			templateUrl : '/actions/nmmXMLModal.html',
			controller : 'ManageNMMXMLInstanceCtrl',
			size : 'lg',
			resolve: {
				projectName: function(){
					return $scope.activeProjectName;
				}
			}			
		});
	};	
	
	$scope.uploadSeedPlugin = function() {		
		var modalInstance = $modal.open({
			templateUrl : '/actions/seedPluginModal.html',
			controller : 'UploadSeedPluginInstanceCtrl',
			size : 'sm',
			resolve: {
				url: function(){
					return '/config/install';					
				}
			}
		});
	};	
});