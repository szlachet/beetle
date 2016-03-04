'use strict'

angular.module('eLeave.leaves', ['eLeave.leaves.controllers']);

angular.module('eLeave.leaves').config(['$stateProvider', '$locationProvider', function ($stateProvider, $locationProvider) {
        $stateProvider.state('about', {
            url: '/about',
            templateUrl: 'modules/leaves/views/about.html',
            controller: 'AboutController'
        }).state('about.seba', {
            url: '/seba',
            templateUrl: 'modules/leaves/views/about-seba.html',
            controller: 'SebaHomeController'
        });
        
        $stateProvider.state('home', {
            url: '/home',
            templateUrl: 'modules/leaves/views/home.html',
            controller: 'HomeController'
        });

        //$locationProvider.html5Mode(true);
    }]);