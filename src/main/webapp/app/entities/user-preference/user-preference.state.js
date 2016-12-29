(function() {
    'use strict';

    angular
        .module('airlineApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('user-preference', {
            parent: 'entity',
            url: '/user-preference?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'airlineApp.userPreference.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-preference/user-preferences.html',
                    controller: 'UserPreferenceController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('userPreference');
                    $translatePartialLoader.addPart('currency');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('user-preference-detail', {
            parent: 'entity',
            url: '/user-preference/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'airlineApp.userPreference.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-preference/user-preference-detail.html',
                    controller: 'UserPreferenceDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('userPreference');
                    $translatePartialLoader.addPart('currency');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'UserPreference', function($stateParams, UserPreference) {
                    return UserPreference.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'user-preference',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('user-preference-detail.edit', {
            parent: 'user-preference-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-preference/user-preference-dialog.html',
                    controller: 'UserPreferenceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserPreference', function(UserPreference) {
                            return UserPreference.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-preference.new', {
            parent: 'user-preference',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-preference/user-preference-dialog.html',
                    controller: 'UserPreferenceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                fromCity: null,
                                toCity: null,
                                departDate: null,
                                returnDate: null,
                                currencyId: null,
                                threshold: null,
                                frequency: null,
                                nextRunDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('user-preference', null, { reload: 'user-preference' });
                }, function() {
                    $state.go('user-preference');
                });
            }]
        })
        .state('user-preference.edit', {
            parent: 'user-preference',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-preference/user-preference-dialog.html',
                    controller: 'UserPreferenceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserPreference', function(UserPreference) {
                            return UserPreference.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-preference', null, { reload: 'user-preference' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-preference.delete', {
            parent: 'user-preference',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-preference/user-preference-delete-dialog.html',
                    controller: 'UserPreferenceDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UserPreference', function(UserPreference) {
                            return UserPreference.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-preference', null, { reload: 'user-preference' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
