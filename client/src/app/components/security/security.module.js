import AuthService from './services/auth.service.js';
import OAuth2Service from './services/oauth2.service.js';
import PrincipalService from './services/principal.service.js';
import authInterceptor from './interceptors/auth.interceptor.js';
import authExpiredInterceptor from './interceptors/auth-expired.interceptor.js';
import authStateConfig from './authentification/authentification.route.js';

export default angular
    .module('sf.security', ['LocalStorageModule'])
    .config(authStateConfig)
    .config(configInterceptors)
    .config(configLocalStorage)
    .run(run)
    .service('AuthService', AuthService)
    .service('OAuth2Service', OAuth2Service)
    .service('PrincipalService', PrincipalService)
    .factory('authInterceptor', authInterceptor)
    .factory('authExpiredInterceptor', authExpiredInterceptor);

function configInterceptors($httpProvider) {
    $httpProvider.interceptors.push('authInterceptor');
    $httpProvider.interceptors.push('authExpiredInterceptor');
}

function configLocalStorage(localStorageServiceProvider) {
    localStorageServiceProvider.setPrefix('sf');
}

function run($rootScope, $state, PrincipalService, AuthService) {
    $rootScope.$on('$stateChangeStart', handleChangeStart);
    $rootScope.$on('$stateChangeSuccess', handleChangeSuccess);
    $rootScope.back = goBack;

    function handleChangeStart(event, toState, toStateParams) {
        $rootScope.toState = toState;
        $rootScope.toStateParams = toStateParams;
    }

    function handleChangeSuccess(event, toState, toParams, fromState, fromParams) {
        $rootScope.previousStateName = fromState.name;
        $rootScope.previousStateParams = fromParams;
        if (PrincipalService.isIdentityResolved()) {
            AuthService.authorize();
        }
    }

    function goBack() {
        if ($state.get($rootScope.previousStateName) === null) {
            $state.go('main.home');
        } else {
            $state.go($rootScope.previousStateName, $rootScope.previousStateParams);
        }
    }
}