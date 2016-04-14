/**
 * 
 */
angular.module("cpgApp").controller('MibTreeMappingCtrl', function($scope, $modal){
	'use strict';
	
	$scope.isEnumeratedValue = function(element){
		return (!_.isUndefined(element.metadata.enumeration) && 
			   !_.isNull(element.metadata.enumeration) && 
			   !_.isEmpty(element.metadata.enumeration));
	}
	 
	$scope.expressionBuilder = function(element, showOid){
		var self = this;
		var modalInstance = $modal.open({
			templateUrl : 'expressionBuilder/expressionBuilderModal.html',
			controller : 'ExpressionBuilderInstanceCtrl',
			size : 'lg',
			resolve: {
				projectName: function(){
					return $scope.activeProjectName;					
				},
				expValue: function(){
					return element.value;
				},		
				lastSelectedNode: function(){
					return $scope.selectedNode;
				},
				showOid: function(){
					return showOid;
				}
			}
		});

		modalInstance.result.then(function(data) {
			element.value = data.value;
			$scope.selectedNode = data.lastSelectedNode;
		});
	};
});