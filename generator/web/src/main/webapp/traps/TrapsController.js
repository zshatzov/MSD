/**
 * 
 */
angular.module("cpgApp").controller('TrapsCtrl', function($scope, $controller, 
		$window, $modal, $log, trapsSvc, TrapsModel, mibSvc, projectLinksSvc){
	'use strict';

	$controller('BaseCtrl', {$scope: $scope});	
	$scope.editMode = false;	
	$scope.eventEntries = [];
	
	trapsSvc.retrieveTrapsMapping('/project/traps/' + 
				$scope.activeProjectName, function(data){
		if(_.isNull(data) || _.isUndefined(data)){
			    mibSvc.getTrapsInfoFromPrimaryMib(projectLinksSvc.getLinkByKey(
						$scope.activeProjectName, 'primaryMibTree').value, function(data){
				$scope.trapsMapping = new TrapsModel(data);				
			});
		}else{
			$scope.trapsMapping = data.trapsMapping;
		}
		
		if(_.isNull($scope.trapsMapping.eventEntries.eventEntry) 
				|| _.isUndefined($scope.trapsMapping.eventEntries.eventEntry)){
			$scope.trapsMapping.eventEntries = {
				eventEntry: {}
			};
		}
				
		trapsSvc.setTrapsMapping($scope.trapsMapping);
		$scope.eventEntries = [].concat($scope.trapsMapping.eventEntries.eventEntry);
		$scope.rowCollection = [].concat($scope.eventEntries);
		$scope.itemsPerPage = 10;
		$scope.calcPages($scope.eventEntries.length, $scope.itemsPerPage);
	});
	
	$scope.save = function(){
		$log.debug("Save traps for project => " + $scope.activeProjectName);
		trapsSvc.save(projectLinksSvc.getLinkByKey(
				$scope.activeProjectName, 'saveTraps').value, 
				$scope.trapsMapping, function(data){
			$log.debug('Successfully saved traps data');
			$window.alert('Traps successfully saved!');
		}, function(resp){
			$window.alert('Save traps failed with status: ' + resp.statusText);
		});
	};
	
	$scope.add = function(){
		$scope.data.inAddMode = true;
		$scope.editMode = false;	
		
		$scope.eventEntry = TrapsModel.createEventEntry();
		$scope.arguments = [];
	};
	
	$scope.edit = function(row){
		$log.debug("Edit traps event entry...");
		var index = $scope.trapsMapping.eventEntries.eventEntry.indexOf(row)
		if(index !== -1){
			$scope.eventEntry = $scope.trapsMapping.eventEntries.eventEntry[index];
			$scope.arguments = [].concat($scope.eventEntry.eventArguments.argument);
			$scope.data.inAddMode = true;
			$scope.editMode = true;
		}
	};	
	
	$scope.delete = function(row){
		var eventEntries = trapsSvc.removeEventEntry($scope.trapsMapping, row);
	  	$scope.rowCollection = [].concat(eventEntries);	    	
	   	$scope.pages = $scope.calcPages(eventEntries.length, $scope.itemsPerPage);	    
	    $scope.data.inAddMode = false;
	};	
	
	$scope.ok = function(){	
	   if(!$scope.editMode &&  trapsSvc.isEventEntryExists($scope.trapsMapping.eventEntries.eventEntry, 
			   	$scope.eventEntry)){
		   $window.alert('Event entry with ID already exists');
		   return;
	   }		
		
	   if(!$scope.editMode){
		   var eventEntries = trapsSvc.addEventEntry($scope.trapsMapping, $scope.eventEntry);
	   	   $scope.rowCollection = [].concat(eventEntries);	    	
	   	   $scope.pages = $scope.calcPages(eventEntries.length,
			   						$scope.itemsPerPage);
	   } 
	 
	   delete $scope.eventEntry;	
	   $scope.data.inAddMode = false;		
	};
	
	$scope.open = function(eventEntry, field) {		
		if(!$scope.bundlesUploaded){
			$window.alert('Please load resource Bundle before you can continue');
			return;
		}
		
		var modalInstance = $modal.open({
			templateUrl : 'resourceBundleModal.html',
			controller : 'ResourceBundleInstanceCtrl',
			size : 'lg',
			resolve: {
				url: function(){
					return projectLinksSvc.getLinkByKey(
							$scope.activeProjectName, 'resourceBundles').value;					
				},
				projectName: function(){
					return $scope.activeProjectName;
				}
			}
		});

		modalInstance.result.then(function(key) {
			eventEntry[field] = key;
		});
	};
	
	$scope.expressionBuilder = function(eventId, showOid){
		var modalInstance = $modal.open({
			templateUrl : 'expressionBuilder/expressionBuilderModal.html',
			controller : 'ExpressionBuilderInstanceCtrl',
			size : 'lg',
			resolve: {
				projectName: function(){
					return $scope.activeProjectName;					
				},
				expValue: function(){
					return eventId;
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
			$scope.eventEntry.id = data.value;
			$scope.selectedNode = data.lastSelectedNode;
		});
	};
	
	$scope.addArgument = function(showOid){
		var modalInstance = $modal.open({
			templateUrl : 'expressionBuilder/expressionBuilderModal.html',
			controller : 'ExpressionBuilderInstanceCtrl',
			size : 'lg',
			resolve: {
				projectName: function(){
					return $scope.activeProjectName;					
				},
				expValue: function(){
					return null;
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
			var _arguments = trapsSvc.addEventArgument($scope.eventEntry, data.value);
			$scope.arguments = [].concat(_arguments);
			$scope.selectedNode = data.lastSelectedNode;
		});		
	};
	
	$scope.deleteArgument = function(row){
		var _arguments = trapsSvc.removeEventArgument($scope.eventEntry, row);
		$scope.arguments = [].concat(_arguments);
	};	
});