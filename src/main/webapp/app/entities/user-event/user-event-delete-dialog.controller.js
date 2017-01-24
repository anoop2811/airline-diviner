(function() {
    'use strict';

    angular
        .module('airlineApp')
        .controller('UserEventDeleteController',UserEventDeleteController);

    UserEventDeleteController.$inject = ['$uibModalInstance', 'entity', 'UserEvent'];

    function UserEventDeleteController($uibModalInstance, entity, UserEvent) {
        var vm = this;

        vm.userEvent = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            UserEvent.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
