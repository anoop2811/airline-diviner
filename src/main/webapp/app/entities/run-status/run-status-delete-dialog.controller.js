(function() {
    'use strict';

    angular
        .module('airlineApp')
        .controller('RunStatusDeleteController',RunStatusDeleteController);

    RunStatusDeleteController.$inject = ['$uibModalInstance', 'entity', 'RunStatus'];

    function RunStatusDeleteController($uibModalInstance, entity, RunStatus) {
        var vm = this;

        vm.runStatus = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            RunStatus.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
