'use strict'

angular.module('eLeave.leaves.controllers', []).controller('AboutController', ['$scope', '$state', function ($scope, $state) {

        $scope.closeAbout = function () {
            $state.go('home');
        };

    }]).controller('HomeController', ['$scope', '$state', function ($scope, $state) {


    }]).controller('SebaHomeController', ['$scope', '$state', function ($scope, $state) {
        $scope.closeAboutSeba = function () {
            $state.go('about');
        };

    }]);