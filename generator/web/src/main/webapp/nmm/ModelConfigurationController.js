/**
 * 
 */
angular.module("cpgApp").controller('ModelConfigurationCtrl', function($scope, $rootScope, $controller, $window, $modal,
			$log, nmmModel, ModelRights, ModelCapabilities, nmmApiTypeSvc, NMMService, projectLinksSvc){

	'use strict';
	
	$controller('BaseCtrl', {$scope: $scope}) 
	$log.debug("Initialize model configurations for project (" + $scope.activeProjectName + ")");
	
	var controller = this; 
	$scope.capabilitiesOptions = ModelCapabilities;
	$scope.selectedCapabilities = [];
	$scope.capabilitiesCustomSettings = { 
			scrollable: true
	};
	$scope.rightsOptions = ModelRights;
	$scope.selectedRights = [];
	$scope.rightsCustomSettings = { 
			scrollable: true
	};
	
	$scope.firmwareFileConfigurationExists = false;
	$scope.templateFileConfigurationExists = false;
	
	$scope.editMode = false;
	
	$scope.imageGroupExists = false;

	$rootScope.$on('nmmMappingsInitialized', function(event, data){
		$scope.modelsConfiguration = nmmModel.getMappings().supportedModelsConfigurationSection.modelConfiguration;
		$log.debug("Have ", $scope.modelsConfiguration.length, " model configurations...");
		
		$scope.itemsPerPage = 10;
		$scope.pages = $scope.calcPages($scope.modelsConfiguration.length, $scope.itemsPerPage);
		$scope.rowCollection = [].concat($scope.modelsConfiguration);
	
	
		//Check if we have existing firmware types entries
		$scope.firmwareFilesTypeOptions = nmmModel.getAllFirmwareFileConfiguration();
		if($scope.firmwareFilesTypeOptions && _.size($scope.firmwareFilesTypeOptions) > 0){
			$scope.firmwareFileConfigurationExists = true;
		}
		
		//Check if we have existing template types entries
		$scope.templateFilesTypeOptions = nmmModel.getAllTemplateFileConfiguration();
		if($scope.templateFilesTypeOptions && _.size($scope.templateFilesTypeOptions) > 0){
			$scope.templateFileConfigurationExists = true;
		}
		
		$scope.imageGroupOptions = nmmModel.getAllImageGroups() || [];
	});
	
	NMMService.isResourceBundleUploaded('/project/bundles/exist/' + 
			$scope.activeProjectName, function(data){
		if(data){
			$scope.bundlesUploaded = true;
		}else{
			$scope.bundlesUploaded = false;
		}
	});
	
	nmmApiTypeSvc.nmmXmlFileUploadedForProject('/project/nmmxml/exist/' + 
			$scope.activeProjectName, function(data){
			$scope.nmmXmlFileUploaded = data || false;
	});
	
	$scope.$on('fileConfigurationAdded', function(event, data){
		$log.debug('Handle file configuration added event...');
		if( data.fileConfiguration.category === 'FIRMWARE'){
			$scope.firmwareFilesTypeOptions.push(data.fileConfiguration.uniqueId);
			$scope.firmwareFileConfigurationExists = true;
		}
		else{
			$scope.templateFilesTypeOptions.push(data.fileConfiguration.uniqueId);
			$scope.templateFileConfigurationExists = true;
		}
	});
	
	$scope.$on('fileConfigurationDeleted', function(event, data){
		if( data.fileConfiguration.category === 'FIRMWARE'){
			var index = $scope.firmwareFilesTypeOptions.indexOf(data.fileConfiguration.uniqueId);
			if(index !== -1){
				$scope.firmwareFilesTypeOptions.splice(index, 1);
			}
			
			if(_.size($scope.firmwareFilesTypeOptions) > 0 ){
				$scope.firmwareFileConfigurationExists = true;
			}else{
				$scope.firmwareFileConfigurationExists = false;
			}
		}
		else{
			var index = $scope.templateFilesTypeOptions.indexOf(data.fileConfiguration.uniqueId);
			if(index !== -1){
				$scope.templateFilesTypeOptions.splice(index, 1);
			}
			
			if(_.size($scope.templateFilesTypeOptions) > 0 ){
				$scope.templateFileConfigurationExists = true;
			}else{
				$scope.templateFileConfigurationExists = false;
			}
		}
	});
	
	$rootScope.$on('nmmXmlModelFileUploaded', function(event, data){
		$scope.nmmXmlFileUploaded = true;
	});
	
	$scope.$on('ImageGroupAdded', function(event, data){
		$scope.imageGroupOptions.push(data.imageGroup.name); 
	});
	
	
	$scope.$on('ImageGroupDeleted', function(event, data){
		var index = _.findIndex($scope.imageGroupOptions, function(option){
			return data.imageGroup.name === option;
		}); 		
		
		if(index !== -1){
			$scope.imageGroupOptions.splice(index, 1);
		}
	});
	
	$scope.add = function(){
		if($scope.bundlesUploaded){
			$scope.modelConfiguration = nmmModel.createSupportedModelConfiguration();
			$scope.data.inAddMode = true;
			$scope.editMode = false;
		}else{
			$window.alert('Please load resource Bundle before you can continue');
		}
	};
	
	$scope.edit = function(row){
		$log.debug("Edit model configuration...");
		var modelConfiguration = nmmModel.getModelConfigurationByUniqueId(row.uniqueId);
		if(modelConfiguration){			
			var capabilities =  NMMService.getCapabilities(modelConfiguration.capabilities);
			$scope.selectedCapabilities = [].concat(capabilities);			
			var rights = NMMService.getConfigurableRights(modelConfiguration.configurableRights);
			$scope.selectedRights = [].concat(rights);				
			$scope.modelConfiguration = modelConfiguration;		
			$scope.data.inAddMode = true;
			$scope.editMode = true;
		}
	};
	
	$scope['delete'] = function(row){	 
		$scope.modelsConfiguration = nmmModel.removeModelConfiguration(row);
	  	$scope.rowCollection = [].concat($scope.modelsConfiguration);	    	
	   	$scope.pages = $scope.calcPages($scope.modelsConfiguration.length, $scope.itemsPerPage);	
	   	$scope.nmmMappings.supportedModelsConfigurationSection.modelConfiguration = 
				[].concat($scope.modelsConfiguration);
	    $scope.data.inAddMode = false;
	};
	
	$scope.ok = function(){		
		if(!$scope.editMode && nmmModel.isModelConfigurationExists($scope.modelConfiguration)){
			   $window.alert('Model configuration unique ID already exists');
			   return;
	   }
		
	   if(!$scope.editMode){		  
		   NMMService.transformAndSaveConfigurableRights($scope.selectedRights, $scope.modelConfiguration);
		   NMMService.transformAndSaveCapabilities($scope.selectedCapabilities, $scope.modelConfiguration);
		   // Save new model configuration
		   $scope.modelsConfiguration = nmmModel.addModelConfiguration($scope.modelConfiguration);
		   $scope.rowCollection = [].concat($scope.modelsConfiguration);	    	
		   $scope.pages = $scope.calcPages($scope.modelsConfiguration.length,
			   						$scope.itemsPerPage);		
	   }else{		 
		   NMMService.transformAndSaveConfigurableRights($scope.selectedRights, $scope.modelConfiguration);
		   NMMService.transformAndSaveCapabilities($scope.selectedCapabilities, $scope.modelConfiguration);
		   // Edit existing model configuration
		   var updateInstance = nmmModel.getModelConfigurationByUniqueId($scope.modelConfiguration.uniqueId);		   
		   if(updateInstance){
			   $scope.modelsConfiguration = nmmModel.removeModelConfiguration(updateInstance);
			   $scope.modelsConfiguration.push(updateInstance);
			   updateInstance.capabilities = [].concat($scope.modelConfiguration.capabilities);
			   updateInstance.configurableRights = [].concat($scope.modelConfiguration.configurableRights);
		   }
	   }
	   
	   $scope.nmmMappings.supportedModelsConfigurationSection.modelConfiguration = 
		   				[].concat($scope.modelsConfiguration);
	   controller.cleanupDrowpdownSelectedOptions();
	   delete $scope.modelConfiguration;
	   $scope.data.inAddMode = false;	
	   $scope.editMode = false;
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
			$scope.modelConfiguration.displayString = key;
		});
	};
 
	$scope.openNmmApiType = function() {
		if(!$scope.nmmXmlFileUploaded){
			$window.alert('Please load NMM XML file before you can continue');
			return;
		}
   		var modalInstance = $modal.open({
   			templateUrl : 'nmm/ApiTypeModal.html',
   			controller : 'ApiTypeInstanceCtrl',
   			size : 'sm',
      			resolve: {
       				url: function(){
       					return projectLinksSvc.getLinkByKey(
    							$scope.activeProjectName, 'manageNmmXml').value;					
       				}
       			}
       		});

      		modalInstance.result.then(function(data) {
       		   $scope.modelConfiguration.uniqueId = data.nmmApiType;
   		});
   	};	
   	
   	$scope.imageGroupExists = function(){
   		return _.size($scope.imageGroupOptions) > 0;
   	};
   	
	controller.cleanupDrowpdownSelectedOptions = function(){ 
		$scope.selectedCapabilities = []; 
		$scope.selectedRights = [];
	};
});