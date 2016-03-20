'use strict'

angular.module('beetle.home', ['beetle.home.controllers']);

angular.module('beetle.home').config(['$stateProvider', function ($stateProvider) {

        $stateProvider.state('home', {
            url: '/home',
            templateUrl: 'modules/home/views/home.html',
            controller: 'HomeController'
        });

    }]);