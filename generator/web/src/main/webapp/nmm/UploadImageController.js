/**
 * 
 */
angular.module('cpgApp').controller('UploadImageCtrl', 
		function($scope, $rootScope, $window, $modal, $log,	nmmModel, NMMService, projectLinksSvc){	
	'use strict';
	
	$log.debug('Initialize UploadImageCtrl...');
	
	$scope.images = {};
	
	$rootScope.$on('nmmMappingsInitialized', function(event, data){		
		$scope.imagesGrouping = nmmModel.getMappings().images.imagesGrouping;
		$scope.images.image = $scope.imagesGrouping; 
		/* Add parent attribute to every image object,
		 * this is needed to allow easy way to delete leaf nodes.
		 */
		nmmModel.enhanceImageObject($scope.imagesGrouping);
	});
	
    $scope.addImageGroup = function(groupName) {
        var groupExists = nmmModel.imageGroupNameExists($scope.imagesGrouping, groupName);
        if (groupExists){
            $window.alert("Group: " + groupName + " already exists.");
            return;
        }
        
        var imageGroup = nmmModel.createImageGroup(groupName);
        $rootScope.$broadcast('ImageGroupAdded', {imageGroup: imageGroup});
        
        AddImageGroupForm.newImageGroup.value = null;
    };
    
	$scope.open = function(imageGroup) {
		var modalInstance = $modal.open({
			templateUrl : 'nmm/uploadImageModal.html',
			controller : 'UploadImageInstanceCtrl',
			size : 'sm',
			resolve: {
				url: function(){
					return projectLinksSvc.getLinkByKey($scope.activeProjectName,
							'uploadImage').value;
				},
				imageGroup: function(){
					return imageGroup;
				},
				projectName: function(){
					return $scope.activeProjectName;
				}
			}
		});

		modalInstance.result.then(function(data) {				
			var image = nmmModel.createImage(data.imageName, data.keyType);				
			image.parent = data.imageGroup;
			nmmModel.addImage(image);
		});
	};
	
	$scope.binaryImageFile = function(item){
	 	return projectLinksSvc.getLinkByKey($scope.activeProjectName, 'image').value +
	 					'?fileName=' + item.name; 
	};
	
	$scope.isLeaf = function(item){		
		if(item.image){
			return false;
		}else{
			return true;
		}		
	}
	
	$scope.removeImage = function(item){		
		nmmModel.removeImage(item);
	};
	
	$scope.removeImageGrouping = function(item){
		if(nmmModel.imageGroupedMappedToModelConfiguration(item)){
			$window.alert("Invalid operation: image group (" + item.name 
					+ ") mapped to a custom model configuration!");
		}else{
			nmmModel.removeImageGrouping(item);
			$rootScope.$broadcast('ImageGroupDeleted', {
				imageGroup:  item
			});
		}
	};
});
