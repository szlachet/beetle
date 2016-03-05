'use strict'

angular.module('beetle.directives',[]);

angular.module('beetle.directives').directive('appVersion',['version',function(version){
	return {
		restrict: 'AE',
		link: function(scope,elem,attrs){
			elem.html(version);
		}
	}	
}]);