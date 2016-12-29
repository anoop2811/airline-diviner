'use strict';

describe('Controller Tests', function() {

    describe('UserEvents Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockUserEvents, MockRunStatus, MockUserPreference;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockUserEvents = jasmine.createSpy('MockUserEvents');
            MockRunStatus = jasmine.createSpy('MockRunStatus');
            MockUserPreference = jasmine.createSpy('MockUserPreference');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'UserEvents': MockUserEvents,
                'RunStatus': MockRunStatus,
                'UserPreference': MockUserPreference
            };
            createController = function() {
                $injector.get('$controller')("UserEventsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'airlineApp:userEventsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
