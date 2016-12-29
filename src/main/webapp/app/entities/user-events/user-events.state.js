(function() {
    'use strict';

    angular
        .module('airlineApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('user-events', {
            parent: 'entity',
            url: '/user-events?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'airlineApp.userEvents.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-events/user-events.html',
                    controller: 'UserEventsController',
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
                    $translatePartialLoader.addPart('userEvents');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('user-events-detail', {
            parent: 'entity',
            url: '/user-events/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'airlineApp.userEvents.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-events/user-events-detail.html',
                    controller: 'UserEventsDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('userEvents');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'UserEvents', function($stateParams, UserEvents) {
                    return UserEvents.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'user-events',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('user-events-detail.edit', {
            parent: 'user-events-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-events/user-events-dialog.html',
                    controller: 'UserEventsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserEvents', function(UserEvents) {
                            return UserEvents.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-events.new', {
            parent: 'user-events',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-events/user-events-dialog.html',
                    controller: 'UserEventsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                runDate: null,
                                fromCity: null,
                                toCity: null,
                                departDate: null,
                                returnDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('user-events', null, { reload: 'user-events' });
                }, function() {
                    $state.go('user-events');
                });
            }]
        })
        .state('user-events.edit', {
            parent: 'user-events',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-events/user-events-dialog.html',
                    controller: 'UserEventsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserEvents', function(UserEvents) {
                            return UserEvents.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-events', null, { reload: 'user-events' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-events.delete', {
            parent: 'user-events',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-events/user-events-delete-dialog.html',
                    controller: 'UserEventsDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UserEvents', function(UserEvents) {
                            return UserEvents.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-events', null, { reload: 'user-events' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
