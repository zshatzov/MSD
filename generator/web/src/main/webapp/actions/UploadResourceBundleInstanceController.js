/**
 * 
 */
angular.module('cpgApp').controller('UploadResourceBundleInstanceCtrl', function ($scope, $http, 
		$modalInstance, $window, localStorageService, Upload, projectLinksSvc, projectName) {
	
	'use strict';
	var controller = this; 
 
	// upload on file select or drop
    $scope.upload = function (file) {   
    	var self = this;
        Upload.upload({
            url: projectLinksSvc.getLinkByKey(
        			projectName, 'uploadResourceBundle').value,
            method: 'POST',
            data: {resourceBundle: file, name: projectName}
        }).then(function (resp) {
            $window.alert("Successfully uploaded resource bundle");
            $scope.$parent.$broadcast('resourceBundleUploaded',{});
            
            if(localStorageService.isSupported){
            	localStorageService.remove(projectName + '.bundles');
            }
            
            UploadResourceBundleForm.reset();
            self.bundle = null;
        }, function (resp) {
            $window.alert('Erorr uploading resource bundle file: ' + resp.statusText);
        });
    };
    
    $scope.validate = function(file){
    	if(file.name.indexOf('.properties') < 0 &&
    	   file.name.indexOf('.PROPERTIES') < 0){
    		return file.name + ' is not a valid resource bundle file';
    	}else{
    		return true;
    	}
    };
	
	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	};
});