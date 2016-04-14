/**
 * 
 */
angular.module("cpgApp").controller("CompletedProjectCtrl", function($scope, $rootScope, $window, $location,
		$controller, $modal, $log, projectSvc, projectLinksSvc){
	'use strict';
	
	$controller('ProjectCtrl', {$scope: $scope});
		
	projectSvc.retrieveAllCompleteProjects('/project/status/COMPLETE', function(data){
		$scope.completedProjects = [].concat(data);
		$scope.rowCollection = [].concat($scope.completedProjects);
	});	   
	
	$scope.previewProject = function(row){
		var selectedProject = row.name;
		
		$log.debug("Generate preview for project => (" + selectedProject + ")"); 
		
		var modalInstance = $modal.open({
   			templateUrl : 'project/previewProjectModal.html',
   			controller : 'PreviewProjectInstanceCtrl',
   			size : 'lg',
			resolve: {
   				url: function(){
   					return '/project/preview/' + selectedProject;					
   				}
   			}
       	});		
	};
	
	$scope.generateProject = function(row){
		var selectedProject =  row.name;
		
		$log.debug("Generate plugin for project => (" + selectedProject + ")");
		
		projectSvc.generateProject('/project/generate/' + selectedProject, function(data){
			var index = _.findIndex($scope.completedProjects, function(item){
				return item.name === selectedProject;
			});
			
			if(index !== -1){
				$scope.completedProjects.splice(index, 1);
				$rootScope.$broadcast('PluginGenerated', {});
			}
			
			$window.alert("Successfully generated plugin for project (" + selectedProject + ")");
		},function(resp){
			$window.alert('Generate project (' + selectedProject + 
					') failed with status: ' + resp.statusText);
		})
	};
});