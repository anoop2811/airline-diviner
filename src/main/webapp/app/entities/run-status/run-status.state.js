(function() {
    'use strict';

    angular
        .module('airlineApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('run-status', {
            parent: 'entity',
            url: '/run-status?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'airlineApp.runStatus.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/run-status/run-statuses.html',
                    controller: 'RunStatusController',
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
                    $translatePartialLoader.addPart('runStatus');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('run-status-detail', {
            parent: 'entity',
            url: '/run-status/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'airlineApp.runStatus.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/run-status/run-status-detail.html',
                    controller: 'RunStatusDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('runStatus');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'RunStatus', function($stateParams, RunStatus) {
                    return RunStatus.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'run-status',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('run-status-detail.edit', {
            parent: 'run-status-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/run-status/run-status-dialog.html',
                    controller: 'RunStatusDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RunStatus', function(RunStatus) {
                            return RunStatus.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('run-status.new', {
            parent: 'run-status',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/run-status/run-status-dialog.html',
                    controller: 'RunStatusDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                statusCode: null,
                                statusDescription: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('run-status', null, { reload: 'run-status' });
                }, function() {
                    $state.go('run-status');
                });
            }]
        })
        .state('run-status.edit', {
            parent: 'run-status',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/run-status/run-status-dialog.html',
                    controller: 'RunStatusDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RunStatus', function(RunStatus) {
                            return RunStatus.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('run-status', null, { reload: 'run-status' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('run-status.delete', {
            parent: 'run-status',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/run-status/run-status-delete-dialog.html',
                    controller: 'RunStatusDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['RunStatus', function(RunStatus) {
                            return RunStatus.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('run-status', null, { reload: 'run-status' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
