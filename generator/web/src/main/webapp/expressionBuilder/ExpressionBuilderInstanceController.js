/**
 * 
 */
angular.module('cpgApp').controller('ExpressionBuilderInstanceCtrl', function ($scope, $modalInstance,
		$log, localStorageService, mibSvc, projectLinksSvc, projectName, expValue, lastSelectedNode, showOid) {
	'use strict';
	
	var controller = this;	 
	controller.expandNodes = function(mibTree){
		var expandedNodes = [];
		_.walk.preorder(mibTree, function(node){
			if(!_.isUndefined(node.state) && node.state.opened){ 
			    expandedNodes.push(node); 
			}
		});
	 
		$scope.expandedNodes = [].concat(expandedNodes);  
	};
	
	$scope.displayValue = expValue? angular.copy(expValue): '';
	$scope.multipleMibFiles = false;
	$scope.expandedNodes = [];
	
	if(lastSelectedNode){
		$scope.selectedNode = lastSelectedNode;
	}
			
	mibSvc.getMibFileNames(projectLinksSvc.getLinkByKey(
			projectName, 'mibFileNames').value, function(data){
		$scope.mibFiles = data;
		
		$scope.selectedMibFileName = _.find($scope.mibFiles, function(candidate){
			return candidate.primary === true;
		});
		
		if(_.size($scope.mibFiles) > 1){
		   $scope.multipleMibFiles = true;
		}
		
		var cachedMibTree = localStorageService.get($scope.selectedMibFileName.mibFileName);
		if(cachedMibTree){
			$scope.mibTree = cachedMibTree;
			controller.expandNodes($scope.mibTree);
		}else{		
			mibSvc.getPrimaryMib(projectLinksSvc.getLinkByKey(
					projectName, 'primaryMibTree').value, function(data){
				$scope.mibTree = data;
				controller.expandNodes($scope.mibTree);
				if(localStorageService.isSupported){
					localStorageService.set($scope.selectedMibFileName.mibFileName, 
							$scope.mibTree);
				}
			});
		}
	});
	
	$scope.multipleMibs = function(){
		return $scope.multipleMibFiles;
	};
	
	$scope.showMibNodeDetails = function(node){
		$scope.selectedNode = node;
		
		var url = projectLinksSvc.getLinkByKey(
				projectName, 'mibDetail').value + '/' + $scope.selectedMibFileName.mibFileName + '/' + node.id + '/'; 
		mibSvc.getDetailsForMibNode(url, function(data){			 
			$scope.mibNodeDetails = data;		
			if(showOid){
				$scope.displayValue = node.id;
			}else{
				$scope.displayValue = node.text;
			}
		}, function(error){
			  $scope.mibNodeDetails = undefined;
		});	
	};
	
	$scope.selectMibTree = function(selection){
		$scope.selectedMibFileName = selection;
		
		var cachedMibTree = localStorageService.get($scope.selectedMibFileName.mibFileName);
		if(cachedMibTree){
			$scope.mibTree = cachedMibTree;
			controller.expandNodes($scope.mibTree);
		}else{		
			var url = projectLinksSvc.getLinkByKey(
					projectName, 'mibTree').value + "/" + selection.mibFileName;
			mibSvc.getMibTreeByFileName(url, function(data){
				$scope.mibTree = data;
				controller.expandNodes($scope.mibTree);
				if(localStorageService.isSupported){
					localStorageService.set($scope.selectedMibFileName.mibFileName,
							$scope.mibTree);
				}
			});
		}
	};
  	
	$scope.buildExpression = function(value){		
		if(_.isNumber(value)){
			var str = String.fromCharCode(value);
			$scope.displayValue = $scope.displayValue.concat(str);
		}else{
			$scope.displayValue = $scope.displayValue.concat(value);
		}
	};
	
	$scope.backSpace = function(){
		$scope.displayValue = $scope.displayValue.slice(0, -1);
	};
	
	$scope.reset = function(){
		$scope.displayValue = '';
		ExpressionBuilderForm.reset();
	};
	
	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	};
	
	$scope.ok = function(value){
		$log.debug('Save calculator expression value => ' + value);
		$modalInstance.close({value: value, lastSelectedNode: $scope.selectedNode});
	};
	
	$scope.treeOptions = {
	    dirSelectable: true,
	    injectClasses: {
	        labelSelected: "treeNodeSelectedBG"
	    }
	};
});