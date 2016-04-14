angular.module('cpgApp').controller('DownloadPluginInstanceCtrl', function ($scope, $http, 
		$modalInstance, $window, $log, projectSvc, url, projectName) {
	'use strict';	
	var controller = this;
	
	controller.calcPages = function(items, itemsPerPage){
		var pgs = Math.floor(items/itemsPerPage);
		var rem = items % itemsPerPage;
		if(rem > 0){
			pgs += 1;
		}
		
		$log.debug("Setting pages to => ", pgs);
		return pgs;		
	};
	
	projectSvc.retrieveGeneratedPlugins(url, function(data){		
		$scope.pluginName = data[0].pluginName;
		$scope.generatedPlugins = [].concat(data);
		$scope.itemsPerPage = 10;
		$scope.pages = controller.calcPages(_.size(data), $scope.itemsPerPage);
		$scope.rowCollection = [].concat(data);
		$log.debug("Have ",_.size(data), " plugin versions for project" + projectName);
	});
	
	$scope.downloadPlugin = function(row){
		var url = '/project/download/' + projectName + "/" + $scope.pluginName +
					"_" + row.pluginVersion;
		return url;
	};

	$scope.deletePlugin = function(row){
		if(_.size($scope.generatedPlugins) < 1){
			return;
		}	
		
		var url = '/project/plugins/' + projectName + "/" + $scope.pluginName + "_" + row.pluginVersion;
		projectSvc.deletePlugin(url, function(data){
			var index = _.findIndex($scope.generatedPlugins, function(item){
				return item.pluginVersion === row.pluginVersion;
			});
			
			$scope.generatedPlugins.splice(index, 1);
			$scope.rowCollection = [].concat($scope.generatedPlugins);
			controller.calcPages(_.size($scope.generatedPlugins, $scope.itemsPerPage));
		}, function(resp){
			$window.alert("Delete plugin for project (" + proejctName + ") failed with status: "
					+ resp.statusText);
		});
	};
	
	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	};
});