(function() {
    'use strict';

    angular
        .module('airlineApp')
        .controller('RunStatusDialogController', RunStatusDialogController);

    RunStatusDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'RunStatus'];

    function RunStatusDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, RunStatus) {
        var vm = this;

        vm.runStatus = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.runStatus.id !== null) {
                RunStatus.update(vm.runStatus, onSaveSuccess, onSaveError);
            } else {
                RunStatus.save(vm.runStatus, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('airlineApp:runStatusUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
