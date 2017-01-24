(function() {
    'use strict';

    angular
        .module('airlineApp')
        .controller('UserPreferenceDetailController', UserPreferenceDetailController);

    UserPreferenceDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserPreference', 'UserEvent', 'User'];

    function UserPreferenceDetailController($scope, $rootScope, $stateParams, previousState, entity, UserPreference, UserEvent, User) {
        var vm = this;

        vm.userPreference = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('airlineApp:userPreferenceUpdate', function(event, result) {
            vm.userPreference = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
