import LoginController from './login/login.controller.js';

export default function authStateConfig($stateProvider) {
    'ngInject';

    $stateProvider.state('authentification', {
        abstract: true,
        templateUrl: 'app/components/security/authentification/authentification.html'
    }).state('authentification.login', {
        url: '/login?logout',
        templateUrl: 'app/components/security/authentification/login/login.html',
        controller: LoginController,
        controllerAs: 'lgn'
    });

}