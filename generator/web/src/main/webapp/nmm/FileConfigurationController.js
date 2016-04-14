/**
 * 
 */
'use strict';

angular.module("cpgApp").controller('FileConfigurationCtrl', function($scope, $rootScope, $controller, $window, 
		$modal, $log, nmmModel, projectLinksSvc){
	
	$controller('BaseCtrl', {$scope: $scope}) 
	$log.debug("Initialize file configurations for project (" + $scope.activeProjectName + ")");
	$scope.editMode = false;
	
	$rootScope.$on('nmmMappingsInitialized', function(event, data){
		$scope.filesConfiguration = nmmModel.getMappings().supportedFilesConfigurationSection.fileConfiguration;
		$log.debug("Have ", $scope.filesConfiguration.length, " file configurations...");
	
		$scope.itemsPerPage = 10;
		$scope.pages = $scope.calcPages($scope.filesConfiguration.length, $scope.itemsPerPage);
		$scope.rowCollection = [].concat($scope.filesConfiguration);
	});
	
	$scope.add = function(){
		$log.debug("Add new file configuration...");
		if($scope.bundlesUploaded){
			$scope.fileConfiguration = nmmModel.createSupportedFileConfiguration();
			$scope.data.inAddMode = true;
			$scope.editMode = false;
		}else{
			$window.alert('Please load resource Bundle before you can continue');
		}
	};
	
	$scope.edit = function(row){
		$log.debug("Edit file configuration...");
		var index = $scope.filesConfiguration.indexOf(row)
		if(index !== -1){
			$scope.fileConfiguration = $scope.filesConfiguration[index];
			$scope.data.inAddMode = true;
			$scope.editMode = true;
		}
	};
	
	$scope['delete'] = function(row){	 
		$scope.filesConfiguration = nmmModel.removeFileConfiguration(row);
	  	$scope.rowCollection = [].concat($scope.filesConfiguration);	    	
	   	$scope.pages = $scope.calcPages($scope.filesConfiguration.length, $scope.itemsPerPage);	    
	    $scope.data.inAddMode = false;
	    $scope.nmmMappings.supportedFilesConfigurationSection.fileConfiguration = 
			   	[].concat($scope.filesConfiguration);
	    $rootScope.$broadcast('fileConfigurationDeleted', {fileConfiguration: row});
	};
	
	$scope.ok = function(){	
	   if(!$scope.editMode && nmmModel.isFileConfigurationExists($scope.fileConfiguration)){
		   $window.alert('File configuration unique ID already exists');
		   return;
	   }		
		
	   if(!$scope.editMode){
		   	$scope.filesConfiguration = nmmModel.addFileConfiguration($scope.fileConfiguration);
	   		$scope.rowCollection = [].concat($scope.filesConfiguration);	    	
	   		$scope.pages = $scope.calcPages($scope.filesConfiguration.length,
			   						$scope.itemsPerPage);	
	   		$rootScope.$broadcast('fileConfigurationAdded', {fileConfiguration: $scope.fileConfiguration});
	   }
	   
	   nmmModel.getMappings().supportedFilesConfigurationSection.fileConfiguration = 
		   	[].concat($scope.filesConfiguration);
	   delete $scope.fileConfiguration;	
	   $scope.data.inAddMode = false;		
	};
	
	$scope.open = function() {
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
			$scope.fileConfiguration.displayString = key;
		});
	};
});