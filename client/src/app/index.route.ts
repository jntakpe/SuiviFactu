/// <reference path="../../.tmp/typings/tsd.d.ts" />

module sf {
    'use strict';

    export class RouterConfig {
        /** @ngInject */
        constructor($stateProvider: ng.ui.IStateProvider, $urlRouterProvider: ng.ui.IUrlRouterProvider) {
            $stateProvider
                .state('home', {
                    url: '/',
                    templateUrl: 'app/main/main.html',
                    controller: 'MainController',
                    controllerAs: 'main'
                });

            $urlRouterProvider.otherwise('/');
        }

    }
}
