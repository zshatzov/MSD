/**
 * 
 */
angular.module("cpgApp").controller('BaseCtrl', function($scope, $log, resourceBundleSvc, projectLinksSvc){
	'use strict';
	
	$scope.calcPages = function(items, itemsPerPage){
		var pgs = Math.floor(items/itemsPerPage);
		var rem = items % itemsPerPage;
		if(rem > 0){
			pgs += 1;
		}
		
		$log.debug("Setting pages to => ", pgs);
		return pgs;		
	};
	
	$scope.data = {}; 
	$scope.data.inAddMode = false;  
	
	$scope.cancel = function(){
		$scope.data.inAddMode = false;
	};
	
	resourceBundleSvc.bundlesUploadedForProject('/project/bundles/exist/' + 
			$scope.activeProjectName, function(data){
		$scope.bundlesUploaded = data || false;
	});
	
	$scope.$on('resourceBundleUploaded', function(event, data){
		$scope.bundlesUploaded = true;
	});
});