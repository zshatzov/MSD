/**
 * 
 */
angular.module('cpgApp').controller('UploadHelpFileInstanceCtrl', function ($scope, $http, 
		$modalInstance, $window, Upload, projectLinksSvc, projectName) {
	
	'use strict';
	var controller = this; 
 
	// upload on file select or drop
    $scope.upload = function (file) {   	
    	var self = this;
        Upload.upload({
            url: projectLinksSvc.getLinkByKey(
    				projectName, 'uploadHelp').value,
            method: 'POST',
            data: {helpFile: file, name: projectName}
        }).then(function (resp) {
            $window.alert("Successfully uploaded help file");
            UploadHelpFileForm.reset();
            self.helpFile = null;
        }, function (resp) {
            $window.alert('Erorr uploading help file: ' + resp.statusText);
        });
    };
    
    $scope.validate = function(file){
       	if(file.name.indexOf('.zip') < 0 && 
       	   file.name.indexOf('.ZIP') < 0){
       		return file.name +  ' is not a valid help file';
       	}else{
       		return true;
       	}
    }
	
	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	};
});