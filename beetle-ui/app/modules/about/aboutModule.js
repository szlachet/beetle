'use strict'

angular.module('beetle.about', ['beetle.about.controllers', 'beetle.about.services']);

angular.module('beetle.about').config(['$stateProvider', function ($stateProvider) {
        $stateProvider.state('about', {
            url: '/about',
            templateUrl: 'modules/about/views/about.html',
            controller: 'AboutController'
        });
    }]);

