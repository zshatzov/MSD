/**
 * 
 */
angular.module('cpgApp').controller('ApiTypeInstanceCtrl', function ($scope, $http, $modalInstance, $log, url) {
	'use strict';
	var controller = this;
	$scope.nmmApiType = [];
	
	controller.calcPages = function(items, itemsPerPage){
		var pgs = Math.floor(items/itemsPerPage);
		var rem = items % itemsPerPage;
		if(rem > 0){
			pgs += 1;
		}
		
		$log.debug("Setting pages to => ", pgs);
		return pgs;		
	};
	
	controller.getSelected = function(){
		return _.find($scope.nmmApiType, function(row){
			return row.isSelected === true;
		});
	};
	
	$http.get(url).success(function(resp){		
		$scope.nmmApiType = [].concat(resp); 
		$scope.itemsPerPage = 10;
		$scope.pages = controller.calcPages(_.size(resp), $scope.itemsPerPage);
		$scope.rowCollection = [].concat(resp);
		$log.debug("Have ",_.size(resp), " NMM api types...");
	});
  
	$scope.ok = function () {
		var selectedRow = controller.getSelected();
		if(selectedRow){
			$modalInstance.close(selectedRow);
		}
	};

	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	};
});