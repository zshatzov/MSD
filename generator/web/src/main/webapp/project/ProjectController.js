/**
 * 
 */
angular.module("cpgApp").controller("ProjectCtrl", function($scope, $window, $location,
		$modal, $log, localStorageService, Upload, projectSvc, projectLinksSvc){
	'use strict';
	
	var self = this;
	
	$scope.project = new projectSvc();
	
	$scope.project.retrieveViewTypes('/device/view', function(data){
		$scope.deviceTypes = [].concat(data);
	});		
	
	$scope.create = function(file){
		$scope.project.saveProject('/project/create',
			   $scope.project, file, function(data){	
		   projectLinksSvc.cacheLinks($scope.project.name, data.data.links);
		   $location.path('edit/' + $scope.project.name);
	   }, function(resp){
		   $window.alert("Create project " + $scope.project.name + 
				   " failed with message: " + resp.data.message);
	   });	
	};	
	
	$scope.createFromMapping = function(mibFile, mappingFile){
		$scope.project.saveProjectWithMapping('/project/createFromMapping',
				   $scope.project, mibFile, mappingFile, function(data){	
			   projectLinksSvc.cacheLinks($scope.project.name, data.data.links);
			   $location.path('edit/' + $scope.project.name);
	    }, function(resp){
			   $window.alert("Create project " + $scope.project.name + 
					   " failed with message: " + resp.data.message);
	   });	
	};
	
	$scope.open = function(row){		
		var selectedProject = row.name;
		
		$log.debug("Open project with name => (" + selectedProject + ")");
		projectSvc.openProject('/project/open/' + selectedProject,
				function(data){
			projectLinksSvc.cacheLinks(selectedProject, data.links);
			$location.path('/edit/' +  selectedProject);
		},function(resp){
			$window.alert("Failed to open project (" + selectedProject + ") with status: " 
					+ resp.statusText);
		});
	};
	
	$scope.delete = function(row){				
		var selectedProject =  row.name;
		
		$log.debug("Delete project => (" + selectedProject+ ")");
		
		if(!$window.confirm("Please confirm delete of project (" + selectedProject + ")")){
			return;
		}
		
		projectSvc.deleteProject('/project/delete/' + selectedProject,
				function(data){	
			var index = _.findIndex($scope.partialProjects, function(project){
				return project.name === selectedProject;
			});
			
			$scope.partialProjects.splice(index, 1);	
			$scope.rowCollection = [].concat($scope.partialProjects);
			if(localStorageService.isSupported){
				localStorageService.remove(selectedProject + '.bundles');
			}
			$window.alert(selectedProject + ' successfully deleted!');
		},function(resp){
			$window.alert("Failed to delete project (" + selectedProject + ") with status: " 
					+ resp.statusText);
		});		
	};
	
    $scope.validate = function(file){
    	if(file.name.indexOf('.ASN') < 0  &&
     	   file.name.indexOf('.asn') < 0){
     		return 'File must end with either ".ASN" or ".asn"';
     	}else{
     		return true;
     	}
    };
    
    $scope.validateMapping = function(file){
    	if(file.name.indexOf('.XML') < 0  &&
     	   file.name.indexOf('.xml') < 0){
     		return 'File must have ".xml" extension';
     	}else{
     		return true;
     	}
    };	
});