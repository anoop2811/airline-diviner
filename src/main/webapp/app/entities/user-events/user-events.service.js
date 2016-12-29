(function() {
    'use strict';
    angular
        .module('airlineApp')
        .factory('UserEvents', UserEvents);

    UserEvents.$inject = ['$resource', 'DateUtils'];

    function UserEvents ($resource, DateUtils) {
        var resourceUrl =  'api/user-events/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.runDate = DateUtils.convertLocalDateFromServer(data.runDate);
                        data.departDate = DateUtils.convertLocalDateFromServer(data.departDate);
                        data.returnDate = DateUtils.convertLocalDateFromServer(data.returnDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.runDate = DateUtils.convertLocalDateToServer(copy.runDate);
                    copy.departDate = DateUtils.convertLocalDateToServer(copy.departDate);
                    copy.returnDate = DateUtils.convertLocalDateToServer(copy.returnDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.runDate = DateUtils.convertLocalDateToServer(copy.runDate);
                    copy.departDate = DateUtils.convertLocalDateToServer(copy.departDate);
                    copy.returnDate = DateUtils.convertLocalDateToServer(copy.returnDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
