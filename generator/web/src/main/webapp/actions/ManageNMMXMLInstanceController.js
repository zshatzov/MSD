/**
 * 
 */
angular.module('cpgApp').controller('ManageNMMXMLInstanceCtrl', function ($scope, $rootScope, $http, 
		$modalInstance, $window, $log, Upload, projectLinksSvc, projectName) {
	'use strict';

	var controller = this;
	
	controller.refreshNMMData = function(){	
		$http.get(projectLinksSvc.getLinkByKey(
				projectName, 'manageNmmXml').value).success(function(data){
			$scope.nmmFiles = [].concat(data);
			$scope.nmmRowCollections = [].concat($scope.nmmFiles);
		});
	};
	
	controller.refreshCommonData = function(){	
		$http.get(projectLinksSvc.getLinkByKey(
				projectName, 'manageCommonData').value).success(function(data){
			$scope.commonFiles = [].concat(data);
			$scope.commonRowCollections = [].concat($scope.commonFiles);
		});
	};
	
	controller.refreshCommonData();
	controller.refreshNMMData();
	
	$scope.deleteCommon = function(row){	
		$http.delete(projectLinksSvc.getLinkByKey(
				projectName, 'manageCommonData').value + "/" + row.fileName);
		$scope.$broadcast('CommonFileDeleted',{});
	};
	
	$scope.deleteNMM = function(row){
		$http.delete(projectLinksSvc.getLinkByKey(
				projectName, 'manageNmmXml').value + "/" + row.fileName);
		$scope.$broadcast('NMMXMLFileDeleted',{});
	};
		
	$scope.$on('CommonFileDeleted',function(event,data){	 
		 controller.refreshCommonData();
	});
	
	$scope.$on('NMMXMLFileDeleted',function(event,data){	 
		 controller.refreshNMMData();
	});
	
	$scope.$on('CommonFileAdded', function(event, data){
		controller.refreshCommonData();
	});
	
	$scope.$on('NMMXMLFileAdded', function(event, data){
		controller.refreshNMMData();
	});
	
	// upload on file select or drop
    $scope.uploadCommon = function (file) {
    	var self = this;
    	Upload.upload({
    		 	url: projectLinksSvc.getLinkByKey(
    					projectName, 'uploadCommonData').value,
    		 	method: 'POST',
    		 	data: {commonData: file, name: projectName}
    	 }).then(function (resp) {
    		 	$log.debug('Success ' + resp.config.data.commonData.name + ' uploaded. Response: ' + resp.data);
    		 	$scope.$broadcast('CommonFileAdded',{});
    		 	UploadCommonXMLFileForm.reset();
    		 	self.commonFile = null;
    	 	},function(resp){
    	 		$window.alert('Erorr uploading common data XML file status: ' + resp.statusText);
    	 	}
    	 );
    };
    
	// upload on file select or drop
    $scope.uploadNMM = function (file) { 
    	 var self = this;
     	 file.upload = Upload.upload({
             url: projectLinksSvc.getLinkByKey(
 					projectName, 'uploadNmmXml').value,
             method: 'POST',
             data: {nmmXml: file, name: projectName}
     	 }).then(function (resp) {
     	       $log.debug('Success ' + resp.config.data.nmmXml.name + ' uploaded. Response: ' + resp.data);
               $scope.$broadcast('NMMXMLFileAdded',{});
               $rootScope.$broadcast('nmmXmlModelFileUploaded', {});
               UploadNMMXMLFileForm.reset();
               self.nmmFile = null;
     	 	},function(resp){
     	 	   $window.alert('Erorr uploading NMM XML file status: ' + resp.statusText);
     	 	}
     	 );
 	};
    
    $scope.validate = function(file){
    	if(file.name.indexOf('.xml') < 0 && 
    	   file.name.indexOf('.XML') < 0){ 
    		return file.name + ' is not a valid XML file';
    	}else{
    		return true;
    	}
    };
	
	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	};
});