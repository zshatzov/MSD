/**
 * Use a JSON document that represents a device view and render
 * a page that uses accordions for layout and text input field(s) that
 * add modal window to allow MIB tree viewer to map the field to a tree node.
 * 
 * 
 */
angular.module('cpgApp').directive('datapointView', function(){
	'use strict';
	return{
		restrict: 'E',
		scope: {
			datapoint: '=',
			activeProjectName: '='
		},
		controller: 'MibTreeMappingCtrl',
		templateUrl: 'datapoint/datapointView.html'
	};
});	