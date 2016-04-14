/**
 * 
 */
angular.module("cpgApp").controller("ActiveProjectCtrl", function($scope, $routeParams, $modal, 
		$window, $controller, $log, projectSvc, projectLinksSvc, datapointSvc){
	'use strict';

	var controller = this;	
	$scope.activeProjectName = $routeParams.projectName;
  
	controller.getDatapoints = function(){
		datapointSvc.retrieveDatapoints(projectLinksSvc.getLinkByKey(
				$scope.activeProjectName, 'datapointsMappings').value, function(data){
			$scope.datapoints = data.dataPointMapping;
		}, function(resp){
			$window.alert('Failed to retreive datapoints for project' +
					$scope.activeProjectName + ' status: ' + resp.statusText);
		});	
	};
		
	controller.handlePageRefresh = function(){
		projectSvc.openProject('/project/open/' + $scope.activeProjectName,
				function(data){
			projectLinksSvc.cacheLinks($scope.activeProjectName, data.links);
			controller.getDatapoints();
		},function(resp){
			$window.alert("Failed to open project (" + $scope.activeProjectName
					+ ") with status: "	+ resp.statusText);
		});
	};
	
	
	if(!_.isUndefined(projectLinksSvc.getLinksForProject($scope.activeProjectName))){
		controller.getDatapoints();
	}else{
		controller.handlePageRefresh();
	}
	

	$scope.save = function(){
		$log.debug('Save datapoints for project => ' + $scope.activeProjectName);
		datapointSvc.save(projectLinksSvc.getLinkByKey(
				$scope.activeProjectName, 'saveDatapoints').value, $scope.datapoints, function(data){
					$log.debug('Successfully saved datapoints');
					$window.alert('Datapoints successfully saved!');
				}, function(resp){
					$window.alert('Save datapoints failed with status: ' + resp.statusText);
				}
		);
	};
	
   /**
    * Validate content on the server.
    */
	$scope.validateContent = function(panelLabel, validateDatapoints){
		$log.debug("Validate project panel => " + panelLabel);
		var modalInstance = $modal.open({
			templateUrl : 'serverValidation/serverValidationModal.html',
			controller : 'ServerValidationInstanceCtrl',
			size : 'sm',
			resolve: {
				url: function(){ 	
					var url;
					switch(panelLabel){					
						case 'Traps': 
							url =  projectLinksSvc.getLinkByKey(
									$scope.activeProjectName, 'validateTraps').value;
							break;
						case 'OBWI': 
							url =  projectLinksSvc.getLinkByKey(
									$scope.activeProjectName, 'validateOBWI').value;	
							break;
						case 'SNMP':
							url = projectLinksSvc.getLinkByKey(
									$scope.activeProjectName, 'validateSNMP').value;
							break;					
						case 'NMM': 
							url = projectLinksSvc.getLinkByKey(
									$scope.activeProjectName, 'validateNMM').value;
							break;
						case 'Custom Code': 
							url = projectLinksSvc.getLinkByKey(
									$scope.activeProjectName, 'validateCustomCode').value;
							break;						
						default: 
							url = projectLinksSvc.getLinkByKey(
									$scope.activeProjectName, 'validateDatapoints').value;
							break;
					}
					
					$log.debug("Server validation URL => " + url);
					return url;
				},
				panelLabel: function(){
					return panelLabel;
				},
				validateDatapoints: function(){
					return validateDatapoints;
				},
				projectName: function(){
					return $scope.activeProjectName;
				}
			}
		});

		modalInstance.result.then(function(key) {
			$scope.modelConfiguration.displayString = key;
		});
	};
});