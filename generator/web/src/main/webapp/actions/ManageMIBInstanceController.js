/**
 * 
 */
angular.module('cpgApp').controller('ManageMIBInstanceCtrl', function ($scope, $http, 
		$window, $modalInstance, $log, localStorageService, Upload, projectLinksSvc, projectName) {
	'use strict';

	var controller = this;
 
	$http.get(projectLinksSvc.getLinkByKey(
			projectName, 'mibFileNames').value).success(function(data){
		$scope.mibFiles = [].concat(data);
		$scope.rowCollections = [].concat($scope.mibFiles);
	});
   
	$scope.delete = function(row){
		$http.delete(projectLinksSvc.getLinkByKey(
				projectName, 'deleteMibFile').value + "/" + row.mibFileName);
		$scope.$broadcast('MIBFileDeleted',{mibFileName: row.mibFileName});
		if(localStorageService.isSupported){
			localStorageService.remove(row.mibFileName);
		}
	};
	
	$scope.$on('MIBFileDeleted',function(event,data){	 
		var index = _.findIndex($scope.mibFiles, function(candidate){
			return candidate.mibFileName === data.mibFileName;
		});
		
		if(index >= 0){
		   $scope.mibFiles.splice(index, 1);
		   $scope.rowCollections = [].concat($scope.mibFiles);
		} 
	});
	
	$scope.$on('MIBFileAdded', function(event, data){
		var fileName = data.mibFileName;
		if(fileName.endsWith('.asn') || fileName.endsWith('.ASN')){
			fileName = fileName.substring(0, fileName.length - 4)
		} 
		 
		$scope.mibFiles.push({mibFileName: fileName, primary: false});
		$scope.rowCollections = [].concat($scope.mibFiles);
	});
	
	// upload on file select or drop
    $scope.uploadMIB = function (file) {
    	var self = this;
        Upload.upload({
            url: projectLinksSvc.getLinkByKey(
    				projectName, 'uploadMibFile').value,
            method: 'POST',
            data: {mibFile: file, name: projectName}
        }).then(function (resp) {
            $log.debug('Success ' + resp.config.data.mibFile.name + ' uploaded. Response: ' + resp.data);
            $scope.$broadcast('MIBFileAdded',{mibFileName: resp.config.data.mibFile.name});
            ManageMIBFilesForm.reset();
            self.mibFile = null;
        }, function (resp) {
            $window.alert('Erorr uploading MIB file: ' + resp.statusText);
        });
    };
    
    $scope.validate = function(file){
    	if(file.name.indexOf('.ASN') < 0  &&
     	   file.name.indexOf('.asn') < 0){
     		return 'File must end with either ".ASN" or ".asn"';
     	}else{
     		return true;
     	}
    };
	
	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	};
});