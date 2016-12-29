(function() {
    'use strict';

    angular
        .module('airlineApp')
        .controller('UserPreferenceDeleteController',UserPreferenceDeleteController);

    UserPreferenceDeleteController.$inject = ['$uibModalInstance', 'entity', 'UserPreference'];

    function UserPreferenceDeleteController($uibModalInstance, entity, UserPreference) {
        var vm = this;

        vm.userPreference = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            UserPreference.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
