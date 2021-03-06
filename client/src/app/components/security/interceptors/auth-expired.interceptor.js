export default function authExpiredInterceptor($q, $injector, localStorageService) {
    return {
        responseError: function (response) {
            if (response.status === 401 && (response.data.error === 'invalid_token' || response.data.error === 'Unauthorized')) {
                localStorageService.remove('token');
                let PrincipalService = $injector.get('PrincipalService');
                if (PrincipalService.isAuthenticated()) {
                    let AuthService = $injector.get('AuthService');
                    AuthService.authorize(true);
                }
            }
            return $q.reject(response);
        }
    };
}