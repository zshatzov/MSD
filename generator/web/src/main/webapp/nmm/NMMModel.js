/**
 * 
 */
angular.module('cpgApp').factory('nmmModel', function($http){
	'use strict';
	var ModelConfiguration = function(){
		var self = this;
		self.uniqueId = '';
		self.cascadedType = '';
		self.classification = 'MeteredPowerConnection';
		self.scope = 'Global';
		self.displayString = '';
		self.imagesGroupingName = '';
		self.supportedFirmwareFile = '';
	    self.supportedTemplateFile = '';
	    self.capabilities = [];
	    self.configurableRights = [];
	};	
	
	
	var FileConfiguration = function(){
		var self = this;
		self.uniqueId = '';
		self.displayString = '';
		self.category = 'CONFIG_TEMPLATE';
		self.familyCode = '';
		self.oemCode = '';
	};	
	
	var Image = function(name, keyType){
		var self = this;
		self.name = name;
		self.keyType = keyType;
	};	
	
	var ImageGroup = function(groupName){
		var self = this;
		self.name = groupName;
		self.image = [];
	};	
	
	var NMMMapping = function(){		
	};	
	
	/** Define behavior for NMMMapping */
	NMMMapping.prototype = {
		addImage: function(image){
			var imageGrouping = _.find(this.images.imagesGrouping, function(imageGroup){
				return imageGroup.name === image.parent;
			});
			
			if(imageGrouping && !this.imageExists(imageGrouping, 
					image.name, image.keyType)){				 
				imageGrouping.image.push(image);
			}
		},
		createSupportedModelConfiguration: function(){
			return new ModelConfiguration();
		},
		createSupportedFileConfiguration: function(){
			return new FileConfiguration();
		},	
		isModelConfigurationExists: function(modelConfiguration){
			return  _.find(this.supportedModelsConfigurationSection.modelConfiguration, function(model){
					return model.uniqueId === modelConfiguration.uniqueId;
			});
		},
		getModelConfigurationByUniqueId: function(uniqueId){
			return _.find(this.supportedModelsConfigurationSection.modelConfiguration, function(model){
				 return model.uniqueId === uniqueId;
			});
		},
		addModelConfiguration: function(modelConfiguration){			
			this.supportedModelsConfigurationSection.modelConfiguration.push(modelConfiguration);
			return this.supportedModelsConfigurationSection.modelConfiguration
		},	
		isFileConfigurationExists: function(fileConfiguration){
			return  _.find(this.supportedFilesConfigurationSection.fileConfiguration, function(file){
					return file.uniqueId === fileConfiguration.uniqueId;
			});
		},
		addFileConfiguration: function(fileConfiguration){
			this.supportedFilesConfigurationSection.fileConfiguration.push(fileConfiguration);
			return this.supportedFilesConfigurationSection.fileConfiguration;
		},	
		getFileConfigurationByUniqueId: function(uniqueId){
			return _.find(this.supportedFileConfigurationSection.fileConfiguration, function(file){
				 return file.uniqueId === uniqueId;
			});
		},
		removeModelConfiguration: function(modelConfiguration){
			var newModelConfigurationList = _.reject(this.supportedModelsConfigurationSection.modelConfiguration,
					function(candidate){
						return candidate.uniqueId === modelConfiguration.uniqueId;
			});
			
			this.supportedModelsConfigurationSection.modelConfiguration = newModelConfigurationList;			
			return [].concat(newModelConfigurationList);
		},
		removeFileConfiguration: function(fileConfiguration){
			var newFileConfigurationList =  _.reject(this.supportedFilesConfigurationSection.fileConfiguration,
				  function(candidate){
					  return candidate.uniqueId === fileConfiguration.uniqueId; 
			});
						
			this.supportedFilesConfigurationSection.fileConfiguration = newFileConfigurationList;
			return [].concat(newFileConfigurationList);
		},
		getAllFirmwareFileConfiguration: function(){
			var configurations =   _.filter(this.supportedFilesConfigurationSection.fileConfiguration,
					function(fileConfiguration){
						return fileConfiguration.category === 'FIRMWARE';
					});
			
			if(configurations){
				return _.map(configurations, function(configuration){
					return configuration.uniqueId;
				});
			}else{
				return [];
			}
		},
		getAllTemplateFileConfiguration: function(){
			var configurations =   _.filter(this.supportedFilesConfigurationSection.fileConfiguration,
					function(fileConfiguration){
				return fileConfiguration.category === 'CONFIG_TEMPLATE';
			});
			
			if(configurations){
				return  _.map(configurations, function(configuration){
					return configuration.uniqueId;
				});
			}else{
				return [];
			}
		},
		getAllImageGrouping: function(){
			var groupNames = [];
			this.images.imagesGrouping.forEach(function(groupName){
				groupNames.push(groupName);
			});
		},
		setMappings: function(nmmMapping){
			angular.extend(this, nmmMapping);
		},
		getMappings: function(){
			return this;
		},
		createImageGroup: function(groupName){
			var imageGroup = new ImageGroup(groupName);
			this.images.imagesGrouping.push(imageGroup);
			return imageGroup;
		},
		imageGroupNameExists: function(imagesGrouping, groupName){
			return _.find(imagesGrouping, function(imageGrouping){
				return groupName === imageGrouping.name;
			});
		},
		imageExists: function(imageGrouping, imageName, keyType){
			return _.find(imageGrouping.image, function(image){
				return image.name === imageName && 
					image.keyType === keyType;
			});
		},
		createImage: function(name, keyType){
			return new Image(name, keyType);
		},
		imageGroupedMappedToModelConfiguration: function(imageGroup){
			var result = _.find(this.supportedModelsConfigurationSection.modelConfiguration,
							function(modelConfiguration){
								return modelConfiguration.imagesGroupingName === imageGroup.name;
						}); 
			
			return result;
		},
		removeImageGrouping: function(imageGroup){
			var index = _.findIndex(this.images.imagesGrouping, function(imageGrouping){
				return imageGrouping.name === imageGroup.name;
			});
			
			if( index !== -1){
				this.images.imagesGrouping.splice(index, 1);
			}
		},
		removeImage: function(image){
			var _imageGrouping = _.find(this.images.imagesGrouping, function(imageGrouping){
				 return imageGrouping.name === image.parent;
			});
			
			if(_imageGrouping){
				var index = _.findIndex(_imageGrouping.image, function(_image){
					return image.name === _image.name &&
							image.keyType === _image.keyType;
				});
				
				if( index !== -1){
					_imageGrouping.image.splice(index, 1); 
				} 
			}
		},
		enhanceImageObject: function(imagesGrouping){
			_.each(imagesGrouping, function(entry){
		    	if(_.size(entry) > 0){
		    		_.map(entry.image, (function(obj){
		    			obj.parent = entry.name;
		    		}));
		    	}
		    });
		},
		getAllImageGroups: function(){
		   return _.map(this.images.imagesGrouping, function(imageGroup){
			   return imageGroup.name;
		   });
		}
	};
	
	return new NMMMapping();
});