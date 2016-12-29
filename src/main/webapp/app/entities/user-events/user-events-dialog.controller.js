(function() {
    'use strict';

    angular
        .module('airlineApp')
        .controller('UserEventsDialogController', UserEventsDialogController);

    UserEventsDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'UserEvents', 'RunStatus', 'UserPreference'];

    function UserEventsDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, UserEvents, RunStatus, UserPreference) {
        var vm = this;

        vm.userEvents = entity;
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
            if (vm.userEvents.id !== null) {
                UserEvents.update(vm.userEvents, onSaveSuccess, onSaveError);
            } else {
                UserEvents.save(vm.userEvents, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('airlineApp:userEventsUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.runDate = false;
        vm.datePickerOpenStatus.departDate = false;
        vm.datePickerOpenStatus.returnDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
