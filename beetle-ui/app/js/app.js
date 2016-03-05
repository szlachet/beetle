'use strict'

var beetleApp = angular.module('beetle',['ui.router', 'beetle.home', 'beetle.controllers', 'beetle.directives', 'beetle.filters', 'beetle.services']);

beetleApp.config(['$locationProvider', '$urlRouterProvider', function($locationProvider, $urlRouterProvider){
        $locationProvider.html5Mode(true);
        
        $urlRouterProvider.otherwise('/home');
}]);

beetleApp.run(['$state', '$urlRouter',function($state, $urlRouter){
      $state.go('home');
}]);



