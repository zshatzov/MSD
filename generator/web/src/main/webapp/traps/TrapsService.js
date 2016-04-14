/**
 * 
 */
angular.module('cpgApp').factory('TrapsModel', function(){
	'use strict';

	var Argument = function(argument, isSymbol){
		this.argumentName = argument;
		if(_.isNull(isSymbol) || _.isUndefined(isSymbol))
			this.isSymbol = false;
		else
			this.isSymbol = isSymbol;
	};

	var EventEntry = function(){
		this.id = '';
		this.nameResourceBundleKey = '';
		this.shortDescriptionResourceBundleKey = '';
		this.longDescriptionResourceBundleKey = '';
		this.elementScope = 'Domain';
		this.elementSeverity = 'Critical';
		this.eventArguments = {
			argument: []
		}
	}	
	
	var TrapsMapping = function(trapMibBase){
		this.pluginName = trapMibBase.pluginName;
		this.enterpriseOID = trapMibBase.enterpriseOID;
		this.trapMIBBase = trapMibBase.trapMIBBase;
		this.eventEntries = {
			eventEntry: []
		};
	};
	
	TrapsMapping.createEventEntry = function(){
		return new EventEntry();
	};
	
	TrapsMapping.createArgument = function(argument, isSymbol){
		return new Argument(argument, isSymbol);
	}
	
	return TrapsMapping;
});

angular.module('cpgApp').factory('trapsSvc', function($http, TrapsModel){
	'use strict';
	
	var TrapsService = function(){
	};
	
	TrapsService.prototype = {
		addEventEntry: function(trapsMapping, eventEntry){
			trapsMapping.eventEntries.eventEntry.push(eventEntry);
			
			return [].concat(trapsMapping.eventEntries.eventEntry);
		},
		removeEventEntry: function(trapsMapping, eventEntry){
			var index = _.findIndex(trapsMapping.eventEntries.eventEntry, function(candidate){
				return eventEntry.id === candidate.id;
			})
			
			if( index >= 0 ){
				trapsMapping.eventEntries.eventEntry.splice(index, 1);
			}
			
			return [].concat(trapsMapping.eventEntries.eventEntry);
		},
		addEventArgument: function(eventEntry, argumentName){
			var argument = TrapsModel.createArgument(argumentName, false);
			eventEntry.eventArguments.argument.push(argument);
			
			return  [].concat(eventEntry.eventArguments.argument);
		},
		removeEventArgument: function(eventEntry, eventArgument){
			var index = _.findIndex(eventEntry.eventArguments.argument, function(candidate){
				return eventArgument.argumentName === candidate.argumentName;
			})
			
			if(index >= 0){
				eventEntry.eventArguments.argument.splice(index, 1);
			}
			
			return [].concat(eventEntry.eventArguments.argument);
		},			
		retrieveTrapsMapping: function(url, callback){
			$http.get(url).success(callback);
		},
		getTrapsMapping: function(){
			return this.trapsMapping;
		},
		setTrapsMapping: function(trapsMapping){
			this.trapsMapping = trapsMapping;
		},
		isEventEntryExists: function(eventEntries, eventEntry){
			_.find(eventEntries, function(candidate){
				return candidate.id === eventEntry.id;
			});
		},
		save: function(url, traps, callback, error){
			$http.put(url, traps).success(callback).error(error);
		}
	}; 
	
	return new TrapsService();	
});
	
	