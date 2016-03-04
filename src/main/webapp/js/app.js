'use strict'

var eLeaveApp = angular.module('eLeave',['ui.router', 'eLeave.leaves', 'eLeave.controllers', 'eLeave.directives', 'eLeave.filters', 'eLeave.services']);

eLeaveApp.config(['$locationProvider', '$urlRouterProvider', function($locationProvider, $urlRouterProvider){
        $locationProvider.html5Mode(true);
        
        $urlRouterProvider.otherwise('/home');
}]);

eLeaveApp.run(['$state', '$urlRouter',function($state, $urlRouter){
      $state.go('home');
}]);



