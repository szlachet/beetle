/* 
 * Copyright 2016 Sebastian Szlachetka.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
(function () {
    'use strict';

    angular.module('beetle').config([
        '$locationProvider',
        '$urlRouterProvider',
        '$stateProvider',
        function ($locationProvider, $urlRouterProvider, $stateProvider) {
            $locationProvider.html5Mode(true);
            $urlRouterProvider.otherwise('/home');

            $stateProvider.state('about', {
                url: '/about',
                templateUrl: 'components/about/views/about.html',
                controller: 'AboutController',
                controllerAs: 'aboutController'
            }).state('home', {
                url: '/home',
                templateUrl: 'components/home/views/home.html',
                controller: 'HomeController',
                controllerAs: 'homeController'
            });
        }]);

    angular.module('beetle').run(['$state', function ($state) {
            $state.go('home');
        }]);
}());

