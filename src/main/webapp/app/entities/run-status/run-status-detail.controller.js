(function() {
    'use strict';

    angular
        .module('airlineApp')
        .controller('RunStatusDetailController', RunStatusDetailController);

    RunStatusDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'RunStatus', 'UserEvent'];

    function RunStatusDetailController($scope, $rootScope, $stateParams, previousState, entity, RunStatus, UserEvent) {
        var vm = this;

        vm.runStatus = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('airlineApp:runStatusUpdate', function(event, result) {
            vm.runStatus = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
