/**
 * 
 */
angular.module('cpgApp').controller('ServerValidationInstanceCtrl', function ($scope, $http, $modalInstance,
			$log, url, panelLabel, validateDatapoints, projectName, serverValidationSvc) {
	'use strict';
	var controller = this;
	$scope.panelLabel = panelLabel;
	$scope.errors = [];
	
	if(validateDatapoints){
		serverValidationSvc.getGroupNameFromLabel('/project/datapoints/groups/' +
				projectName, panelLabel, function(groupName){
			var _url = url +  '/' + groupName;
			$log.debug("Returning url for datapoints validation +> " + _url);			
			serverValidationSvc.validate(_url, function(resp){
				if(_.size(resp) > 0){ 
					$scope.errors = [].concat(resp);
				}	
			}); 
		})		
	}else{
		serverValidationSvc.validate(url, function(resp){
			if(_.size(resp) > 0){ 
				$scope.errors = [].concat(resp);
			}	
		}); 
	}

	 
	$scope.close = function () {
	    $modalInstance.dismiss('close');
	};
	
	$scope.errorsExist = function(){
		if($scope.errors && _.size($scope.errors) > 0){
			return true;
		}else{
			return false;
		}	
	};
});