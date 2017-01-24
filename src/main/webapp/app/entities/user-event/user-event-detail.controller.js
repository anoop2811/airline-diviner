(function() {
    'use strict';

    angular
        .module('airlineApp')
        .controller('UserEventDetailController', UserEventDetailController);

    UserEventDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserEvent', 'RunStatus', 'UserPreference'];

    function UserEventDetailController($scope, $rootScope, $stateParams, previousState, entity, UserEvent, RunStatus, UserPreference) {
        var vm = this;

        vm.userEvent = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('airlineApp:userEventUpdate', function(event, result) {
            vm.userEvent = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
