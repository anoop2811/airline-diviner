(function() {
    'use strict';
    angular
        .module('airlineApp')
        .factory('UserEvent', UserEvent);

    UserEvent.$inject = ['$resource', 'DateUtils'];

    function UserEvent ($resource, DateUtils) {
        var resourceUrl =  'api/user-events/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.runDate = DateUtils.convertLocalDateFromServer(data.runDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.runDate = DateUtils.convertLocalDateToServer(copy.runDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.runDate = DateUtils.convertLocalDateToServer(copy.runDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
