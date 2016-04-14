/**
 * 
 */
	
angular.module('cpgApp').controller('NMMMappingCtrl', 
			function($scope, $rootScope, $controller, $window, $modal,
					$log, nmmModel, NMMService, projectLinksSvc){	
	'use strict';
	
	$controller('BaseCtrl', {$scope: $scope});
	$log.debug('Initialize nmmMappingCtrl...');	
	
	NMMService.retrieveNMMMapping('/project/nmm/' + 
			$scope.activeProjectName, function(data) {	
		nmmModel.setMappings(data.nmmMapping);
		$scope.nmmMappings = nmmModel.getMappings();
		projectLinksSvc.cacheLinks($scope.activeProjectName, data.links);
		$rootScope.$broadcast('nmmMappingsInitialized');
	}, function(resp){
	   	$widnow.alert('Retrieve NMM data failed with status: ' + resp.statusText);
	});
	
	$scope.save = function(){
		$log.debug("Save NMM data for project => " + $scope.activeProjectName);
		NMMService.save(projectLinksSvc.getLinkByKey(
				$scope.activeProjectName, 'saveNMM').value, 
				$scope.nmmMappings, function(data){
			$log.debug('Successfully saved NMM data');
			$window.alert('NMM successfully saved!');
		}, function(resp){
			$window.alert('Save NMM failed with status: ' + resp.statusText);
		});
	};	
	
   	$scope.openIdentitySection = function(field) {
		if(!$scope.bundlesUploaded){
			$window.alert('Please load resource Bundle before you can continue');
			return;
		}		
   		var modalInstance = $modal.open({
   			templateUrl : 'resourceBundleModal.html',
   			controller : 'ResourceBundleInstanceCtrl',
   			size : 'lg',
      			resolve: {
       				url: function(){
       					return projectLinksSvc.getLinkByKey(
    							$scope.activeProjectName, 'resourceBundles').value;					
       				},
       				projectName: function(){
       					return $scope.activeProjectName;
       				}
       			}
       		});

      		modalInstance.result.then(function(key) {
       		   $scope.nmmMappings.identitySection[field] = key;
   		});
   	};
});
