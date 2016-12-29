(function() {
    'use strict';

    angular
        .module('airlineApp')
        .controller('UserEventsDetailController', UserEventsDetailController);

    UserEventsDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserEvents', 'RunStatus', 'UserPreference'];

    function UserEventsDetailController($scope, $rootScope, $stateParams, previousState, entity, UserEvents, RunStatus, UserPreference) {
        var vm = this;

        vm.userEvents = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('airlineApp:userEventsUpdate', function(event, result) {
            vm.userEvents = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
