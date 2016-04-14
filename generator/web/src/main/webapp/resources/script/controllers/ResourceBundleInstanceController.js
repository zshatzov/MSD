/**
 * 
 */
angular.module('cpgApp').controller('ResourceBundleInstanceCtrl', function ($scope, 
		$http, localStorageService, $modalInstance, $log, url, projectName) {
	'use strict';
	
	var controller = this;
	$scope.bundle = [];
	
	controller.calcPages = function(items, itemsPerPage){
		var pgs = Math.floor(items/itemsPerPage);
		var rem = items % itemsPerPage;
		if(rem > 0){
			pgs += 1;
		}
		
		$log.debug("Setting pages to => ", pgs);
		return pgs;		
	};
	
	$scope.getSelected = function(){
		return _.find($scope.bundle, function(row){
			return row.isSelected === true;
		});
	};	
	
	controller.bundles = localStorageService.get(projectName + '.bundles');
	if(controller.bundles){
		$scope.bundle = controller.bundles;
		$scope.itemsPerPage = 10;
		$scope.pages = controller.calcPages(Object.keys($scope.bundle).length, $scope.itemsPerPage);
		$scope.rowCollection = [].concat($scope.bundle);
		$log.debug("Have ",Object.keys($scope.bundle).length, " resource bundle keys...");
	}else{	
		$http.get(url).success(function(resp){		
			angular.forEach(resp, function(value, key){
				$scope.bundle.push({key: key, value: value});
			});
			
			localStorageService.set(projectName + '.bundles', $scope.bundle);			
			$scope.itemsPerPage = 10;
			$scope.pages = controller.calcPages(Object.keys($scope.bundle).length, $scope.itemsPerPage);
			$scope.rowCollection = [].concat($scope.bundle);
			$log.debug("Have ",Object.keys($scope.bundle).length, " resource bundle keys...");
		});
	}
  
	$scope.ok = function () {
		var selectedRow = $scope.getSelected();
		if(selectedRow){
			$modalInstance.close(selectedRow.key);
		}
	};

	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	};
});