import LoginController from './login/login.controller.js';
import SignupController from './signup/signup.controller.js';
import ForgotController from './forgot/forgot.controller.js';
import ActivateController from './activate/activate.controller.js';

export default function authStateConfig($stateProvider) {
    'ngInject';

    $stateProvider.state('auth', {
        abstract: true,
        templateUrl: 'app/components/security/authentification/authentification.html'
    }).state('auth.login', {
        url: '/login?logout&register&forgot',
        templateUrl: 'app/components/security/authentification/login/login.html',
        controller: LoginController,
        controllerAs: 'lgn'
    }).state('auth.signup', {
        url: '/register',
        templateUrl: 'app/components/security/authentification/signup/signup.html',
        controller: SignupController,
        controllerAs: 'sup'
    }).state('auth.forgotpwd', {
        url: '/forgotpwd',
        templateUrl: 'app/components/security/authentification/forgot/forgot.html',
        controller: ForgotController,
        controllerAs: 'fgt'
    }).state('auth.activate', {
        url: '/activate?key',
        templateUrl: 'app/components/security/authentification/activate/activate.html',
        controller: ActivateController,
        controllerAs: 'act'
    });

}