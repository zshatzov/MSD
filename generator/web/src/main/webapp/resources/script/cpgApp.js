

var cpgApp = angular.module('cpgApp', ['ui.bootstrap', 'ngRoute', 'ngSanitize', 'adaptv.adaptStrap',
                                     'ngFileUpload', 'angularjs-dropdown-multiselect', 'smart-table', 
                                     'LocalStorageModule', 'treeControl', 'angular-loading-bar']);

cpgApp.config(function($routeProvider, $locationProvider){
  $routeProvider
  	.when('/', {
        templateUrl: 'project/projectDashboard.html'
    }).    
    when('/new', {
        templateUrl: 'project/newProject.html',
        controller: 'ProjectCtrl'
    }).
    when('/fromMapping', {
        templateUrl: 'project/newProjectFromMapping.html',
        controller: 'ProjectCtrl'
    }).	 
    when('/edit/:projectName', {
        templateUrl: 'project/ProjectPage.html',
        controller: 'ActiveProjectCtrl'
    }).
    otherwise({
        redirectTo: '/'
    });	
});

cpgApp.config(['$logProvider', 'localStorageServiceProvider'
              , function($logProvider, localStorageServiceProvider){
	$logProvider.debugEnabled(false);
	localStorageServiceProvider.setPrefix('cpg'
			).setStorageType('localStorage');
}]);

cpgApp.constant('CheckValidation', {title: 'Right Click To Check Validation'});

cpgApp.constant('ModelCapabilities',[
   {label: 'PDU Power On', id: 'POWER_ON'},                                  
   {label: 'PDU Power Off', id: 'POWER_OFF'},
   {label: 'PDU Power Cycle', id: 'POWER_CYCLE'},
   {label: 'PDU Reboot', id: 'REBOOT'} 
]);

cpgApp.constant('ModelRights',[
    {label: 'Administer local Accounts', id: 'ADMINISTER_LOCAL_ACCOUNTS'},                                
    {label: 'Configure', id: 'CONFIGURE'},
    {label: 'Control Target Device Power', id: 'CONTROL_TARGET_DEVICE_POWER'},
    {label: 'Flash', id: 'FLASH'},
    {label: 'Reboot', id: 'REBOOT'},
    {label: 'View', id: 'VIEW'}
 ]);

cpgApp.constant('KeyTypeOptions',[
   {label: 'launchStandard', value: 'launchStandard'},
   {label: 'listStandard', value: 'listStandard'},
   {label: 'toolStandard', value: 'toolStandard'}                            
]);

cpgApp.run(function($rootScope, CheckValidation, KeyTypeOptions){
	$rootScope.VALIDATION_TITLE = CheckValidation.title;
	$rootScope.KEY_TYPE_OPTIONS = KeyTypeOptions
});