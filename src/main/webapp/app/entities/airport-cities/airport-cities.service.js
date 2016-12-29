(function() {
    'use strict';
    angular
        .module('airlineApp')
        .factory('AirportCities', AirportCities);

    AirportCities.$inject = ['$resource'];

    function AirportCities ($resource) {
        var resourceUrl =  'api/airport-cities/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            }
        });
    }
})();
