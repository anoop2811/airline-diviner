(function() {
    'use strict';
    angular
        .module('airlineApp')
        .factory('RunStatus', RunStatus);

    RunStatus.$inject = ['$resource'];

    function RunStatus ($resource) {
        var resourceUrl =  'api/run-statuses/:id';

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
            },
            'update': { method:'PUT' }
        });
    }
})();
