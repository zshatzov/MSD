/**
 * 
 */
angular.module("cpgApp").controller("GeneratedProjectCtrl", function($scope, $rootScope, $window, $location,
		$controller, $modal, $log, projectSvc, projectLinksSvc){
	'use strict';
	
	$controller('ProjectCtrl', {$scope: $scope});
	
	projectSvc.retrieveAllGeneratedProjects('/project/status/GENERATED', function(data){
		$scope.generatedProjects = [].concat(data);	
		$scope.rowCollection = [].concat($scope.generatedProjects);
	}); 
	
	$rootScope.$on('PluginGenerated', function(event, data){
		projectSvc.retrieveAllGeneratedProjects('/project/status/GENERATED', function(data){
			$scope.generatedProjects = [].concat(data);
			$scope.rowCollection = [].concat($scope.generatedProjects);
		}); 
	});
	
	$scope.downloadPlugin = function(row){
		var selectedProject =  row.name;
		
		$log.debug("Download plugin for project => (" + selectedProject + ")");
		
		var modalInstance = $modal.open({
   			templateUrl : 'project/pluginDownloadModal.html',
   			controller : 'DownloadPluginInstanceCtrl',
   			size : 'lg',
			resolve: {
   				url: function(){
   					return '/project/plugins/' + selectedProject;					
   				},
   				projectName: function(){
   					return selectedProject;
   				}
   			}
       	});		
	};
	
	$scope.downloadProject = function(row){
		var selectedProject = row.name;
		
		$log.debug("Generate download URL for project => (" + selectedProject + ")");
		
		return '/project/download/' + selectedProject;
	};
});