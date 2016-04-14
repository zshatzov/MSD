/**
 * 
 */
angular.module("cpgApp").controller('CustomCodeCtrl', function($scope, $window, $log, Upload,
		customCodeSvc, CustomCodeModel, projectLinksSvc){
	'use strict';
	
	customCodeSvc.getUploadedFirmware('/project/firmware/' + 
				$scope.activeProjectName, function(data){
		if(data && _.size(data) > 0){
		   $scope.firmwareFiles = [].concat(data);
		   $scope.rowCollection = [].concat(data);
		   $scope.firmwareUploaded = true;		 
		}else{
			$scope.firmwareUploaded = false;
		}				
	});	
	  
	customCodeSvc.retrieveCustomCodeMapping('/project/customCode/' +
				$scope.activeProjectName, function(data){
		if(data.customCodeInformation){
		  $scope.customCode = data.customCodeInformation;
		}else{
		  $scope.customCode = new CustomCodeModel();				 
		}
		
		customCodeSvc.setCustomCodeMapping($scope.customCode);
	});		   
	
	$scope.$on('FirmwareFileUploaded', function(event, args){
		customCodeSvc.getUploadedFirmware(projectLinksSvc.getLinkByKey(
				$scope.activeProjectName, 'uploadedFirmware').value, function(data){
			if(data){
				$scope.firmwareFiles = [].concat(data);
				$scope.rowCollection = [].concat(data);
				$scope.firmwareUploaded = true;
			}
		});	
	});
	
	$scope.$on('FirmwareFileDeleted', function(event, args){ 
		$scope.firmwareFiles = [];
		$scope.rowCollection = [];
		$scope.customCode =  new CustomCodeModel();
		customCodeSvc.setCustomCodeMapping($scope.customCode);
		$scope.firmwareUploaded = false; 
	});
	
	$scope.save = function(){
		$log.debug("Save custom code for project => " + $scope.activeProjectName);
		customCodeSvc.save(projectLinksSvc.getLinkByKey(
				$scope.activeProjectName, 'saveCustomCode').value, 
				$scope.customCode, function(data){
			$log.debug('Successfully saved custom code data');
			$window.alert('Custom code successfully saved!');
		}, function(resp){
			$window.alert('Save custom code failed with status: ' + resp.statusText);
		});
	};
	
	 // upload on file select
    $scope.upload = function (file) { 
    	var self = this;
        Upload.upload({
            url: projectLinksSvc.getLinkByKey(
    				$scope.activeProjectName, 'uploadCustomCode').value,
            method: 'POST',
            data: {'customJar': file, 'name': $scope.activeProjectName}
        }).then(function (resp) {
        	$scope.$broadcast('FirmwareFileUploaded',{});
        	UploadCustomCodeForm.reset();
        	self.firmwareFile = null;
        }, function (resp) {
        	$scope.firmwareUploaded = false;
            $window.alert('Error uploading firmware file, status: ' + resp.statusText);
        });
    };
    
    $scope.delete = function(row){
    	customCodeSvc.deleteCustomCode(projectLinksSvc.getLinkByKey(
				$scope.activeProjectName, 'deleteCustomCode').value);
    	$scope.$broadcast('FirmwareFileDeleted',{});
    };
});