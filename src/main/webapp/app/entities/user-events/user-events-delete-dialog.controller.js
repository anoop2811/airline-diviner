(function() {
    'use strict';

    angular
        .module('airlineApp')
        .controller('UserEventsDeleteController',UserEventsDeleteController);

    UserEventsDeleteController.$inject = ['$uibModalInstance', 'entity', 'UserEvents'];

    function UserEventsDeleteController($uibModalInstance, entity, UserEvents) {
        var vm = this;

        vm.userEvents = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            UserEvents.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
