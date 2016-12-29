(function() {
    'use strict';
    angular
        .module('airlineApp')
        .factory('UserPreference', UserPreference);

    UserPreference.$inject = ['$resource', 'DateUtils'];

    function UserPreference ($resource, DateUtils) {
        var resourceUrl =  'api/user-preferences/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.departDate = DateUtils.convertLocalDateFromServer(data.departDate);
                        data.returnDate = DateUtils.convertLocalDateFromServer(data.returnDate);
                        data.nextRunDate = DateUtils.convertLocalDateFromServer(data.nextRunDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.departDate = DateUtils.convertLocalDateToServer(copy.departDate);
                    copy.returnDate = DateUtils.convertLocalDateToServer(copy.returnDate);
                    copy.nextRunDate = DateUtils.convertLocalDateToServer(copy.nextRunDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.departDate = DateUtils.convertLocalDateToServer(copy.departDate);
                    copy.returnDate = DateUtils.convertLocalDateToServer(copy.returnDate);
                    copy.nextRunDate = DateUtils.convertLocalDateToServer(copy.nextRunDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
