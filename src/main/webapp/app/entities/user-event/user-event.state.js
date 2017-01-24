(function() {
    'use strict';

    angular
        .module('airlineApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('user-event', {
            parent: 'entity',
            url: '/user-event?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'airlineApp.userEvent.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-event/user-events.html',
                    controller: 'UserEventController',
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
                    $translatePartialLoader.addPart('userEvent');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('user-event-detail', {
            parent: 'entity',
            url: '/user-event/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'airlineApp.userEvent.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-event/user-event-detail.html',
                    controller: 'UserEventDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('userEvent');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'UserEvent', function($stateParams, UserEvent) {
                    return UserEvent.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'user-event',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('user-event-detail.edit', {
            parent: 'user-event-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-event/user-event-dialog.html',
                    controller: 'UserEventDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserEvent', function(UserEvent) {
                            return UserEvent.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-event.new', {
            parent: 'user-event',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-event/user-event-dialog.html',
                    controller: 'UserEventDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                bestOnwardPath: null,
                                bestReturnPath: null,
                                bestPrice: null,
                                runDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('user-event', null, { reload: 'user-event' });
                }, function() {
                    $state.go('user-event');
                });
            }]
        })
        .state('user-event.edit', {
            parent: 'user-event',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-event/user-event-dialog.html',
                    controller: 'UserEventDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserEvent', function(UserEvent) {
                            return UserEvent.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-event', null, { reload: 'user-event' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-event.delete', {
            parent: 'user-event',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-event/user-event-delete-dialog.html',
                    controller: 'UserEventDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UserEvent', function(UserEvent) {
                            return UserEvent.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-event', null, { reload: 'user-event' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
