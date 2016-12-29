(function() {
    'use strict';

    angular
        .module('airlineApp')
        .controller('UserPreferenceDetailController', UserPreferenceDetailController);

    UserPreferenceDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserPreference', 'UserEvents', 'User'];

    function UserPreferenceDetailController($scope, $rootScope, $stateParams, previousState, entity, UserPreference, UserEvents, User) {
        var vm = this;

        vm.userPreference = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('airlineApp:userPreferenceUpdate', function(event, result) {
            vm.userPreference = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
