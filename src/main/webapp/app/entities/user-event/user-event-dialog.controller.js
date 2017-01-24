(function() {
    'use strict';

    angular
        .module('airlineApp')
        .controller('UserEventDialogController', UserEventDialogController);

    UserEventDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'UserEvent', 'RunStatus', 'UserPreference'];

    function UserEventDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, UserEvent, RunStatus, UserPreference) {
        var vm = this;

        vm.userEvent = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.runstatuses = RunStatus.query();
        vm.userpreferences = UserPreference.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.userEvent.id !== null) {
                UserEvent.update(vm.userEvent, onSaveSuccess, onSaveError);
            } else {
                UserEvent.save(vm.userEvent, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('airlineApp:userEventUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.runDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
