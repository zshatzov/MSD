/**
 * 
 */
'use strict';
angular.module('cpgApp').controller('UploadImageInstanceCtrl',
		function ($scope, $http, $window, $modalInstance, $log, Upload,
		projectLinksSvc, imageGroup, url, projectName) {
	var controller = this;
	
	$scope.keyTypeOption = 'launchStandard';
	$scope.imageGroup = imageGroup;
	
	// upload on file select or drop
    $scope.upload = function (file, keyType) {
        Upload.upload({
            url: url,
            method: 'POST',
            data: {image: file, name: projectName}
        }).then(function (resp) {
            $log.debug('Success ' + resp.config.data.image.name + ' uploaded. Response: ' + resp.data);
            $modalInstance.close(
            		{imageGroup: $scope.imageGroup.name, imageName: resp.config.data.image.name, 
            			keyType: keyType});
        }, function (resp) {
            $window.alert('Erorr uploading image file: ' + resp.statusText);
        });
    };

	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	};
});