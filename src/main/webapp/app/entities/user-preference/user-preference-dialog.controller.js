(function() {
    'use strict';

    angular
        .module('airlineApp')
        .controller('UserPreferenceDialogController', UserPreferenceDialogController);

    UserPreferenceDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'UserPreference', 'UserEvent', 'User', 'AirportCities'];

    function UserPreferenceDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, UserPreference, UserEvent, User, AirportCities) {
        var vm = this;

        vm.userPreference = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.userevents = UserEvent.query();
        vm.users = User.query();
        vm.airports = AirportCities.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.userPreference.id !== null) {
                UserPreference.update(vm.userPreference, onSaveSuccess, onSaveError);
            } else {
                UserPreference.save(vm.userPreference, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('airlineApp:userPreferenceUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.departDate = false;
        vm.datePickerOpenStatus.returnDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
