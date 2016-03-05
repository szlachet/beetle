'use strict'

angular.module('beetle.home', ['beetle.home.controllers']);

angular.module('beetle.home').config(['$stateProvider', '$locationProvider', function ($stateProvider, $locationProvider) {
        $stateProvider.state('about', {
            url: '/about',
            templateUrl: 'modules/home/views/about.html',
            controller: 'AboutController'
        }).state('about.seba', {
            url: '/seba',
            templateUrl: 'modules/home/views/about-seba.html',
            controller: 'SebaHomeController'
        });
        
        $stateProvider.state('home', {
            url: '/home',
            templateUrl: 'modules/home/views/home.html',
            controller: 'HomeController'
        });

    }]);