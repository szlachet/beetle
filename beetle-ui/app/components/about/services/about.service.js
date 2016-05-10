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
(function(){
    
    angular.module('beetle.about.services')
            .value('GET_AUTHOR_ENDPOINT', '/beetle/resources/authors/:id')
          //.value('GET_AUTHOR_ENDPOINT', 'http://localhost:8080/beetle/resources/authors/:id')
            .factory('aboutService', aboutService);
    aboutService.$inject = ['$resource', 'GET_AUTHOR_ENDPOINT'];
    
    function aboutService($resource, GET_AUTHOR_ENDPOINT) {
        var service = {
            getAuthor : getAuthor
        };
        return service;
        //////////////////////////////////////////

        function getAuthor() {
            return $resource(GET_AUTHOR_ENDPOINT).get({id: '56ef0f2f627ae8ccf7b159f9'});
        }
    }
}());

