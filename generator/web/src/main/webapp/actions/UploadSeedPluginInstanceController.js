/**
 * 
 */
angular.module('cpgApp').controller('UploadSeedPluginInstanceCtrl', function ($scope, $http, 
		$modalInstance, $window, Upload, url) {
	
	'use strict';
	var controller = this;
	
	// upload on file select or drop
    $scope.upload = function (file) {    
    	var self = this;
        Upload.upload({
            url: url,
            method: 'POST',
            data: {seedPlugin: file}
        }).then(function (resp) {
        	//reset form values
        	UploadSeedPluginForm.reset();
        	self.seedPlugin = null;
            $window.alert("Successfully uploaded Seed Plugin file");
        }, function (resp) {
            $window.alert('Erorr uploading Seed Plugin file: ' + resp.statusText);
        });
    };
    
    $scope.validate = function(file){
       	if(file.name.indexOf('.zip') < 0 && 
       	   file.name.indexOf('.ZIP') < 0){
       		return file.name +  ' is not a valid Seed Plugin file';
       	}else{
       		return true;
       	}
    }
	
	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	};
});