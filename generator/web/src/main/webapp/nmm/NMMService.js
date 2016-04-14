angular.module('cpgApp').service('NMMService', function($http) {
	return {
		transformAndSaveCapabilities : function(capabilities,
				modelConfiguration) {
			// transform before saving
			var _capabilities = _.map(capabilities, function(capability) {
				return capability.id;
			});

			modelConfiguration.capabilities = [].concat(_capabilities);
		},
		getCapabilities : function(capabilities) {
			// transform before displaying
			return _.map(capabilities, function(capability) {
				return {
					id : capability
				};
			});
		},
		transformAndSaveConfigurableRights : function(rights,
				modelConfiguration) {
			// transform before saving
			var _rights = _.map(rights, function(right) {
				return right.id;
			});

			modelConfiguration.configurableRights = [].concat(_rights);
		},
		getConfigurableRights : function(configurableRights) {
			// transform before displaying
			return _.map(configurableRights, function(right) {
				return {
					id : right
				};
			});
		},
		isResourceBundleUploaded: function(url, callback){
			$http.get(url).success(callback);
		},
		save: function(url, nmmMappings, callback, error){
			$http.put(url, nmmMappings).success(callback).error(error);
		},
		retrieveNMMMapping: function(url, callback, error){
			$http.get(url).success(callback).error(error);
		}
	}
});