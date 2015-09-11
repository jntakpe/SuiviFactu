export default class AuthService {

    constructor($rootScope, $state, $q, PrincipalService, OAuth2Service, toastr) {
        'ngInject';
        this.$rootScope = $rootScope;
        this.$state = $state;
        this.$q = $q;
        this.PrincipalService = PrincipalService;
        this.OAuth2Service = OAuth2Service;
        this.toastr = toastr;
    }

    login(credentials, callback) {
        var cb = callback || angular.noop, deferred = this.$q.defer();
        this.OAuth2Service.login(credentials).then((response) => {
                this.PrincipalService.resolveIdentity(true).then(() => deferred.resolve(response));
                return cb();
            }
        ).catch((err) => {
                this.logout();
                deferred.reject(err);
                return cb(err);
            }
        );
        return deferred.promise;
    }

    logout() {
        this.OAuth2Service.logout();
        this.PrincipalService.authenticate(null);
        this.$state.go('auth.login', {logout: true});
    }

    authorize(force) {
        return this.PrincipalService.resolveIdentity(force).then(identity => {
                let data = this.$rootScope.toState.data;
                if (data && data.roles && data.roles.length > 0 && !this.PrincipalService.isInAnyRole(data.roles)) {
                    if (this.PrincipalService.isAuthenticated()) {
                        this.toastr.error('Vous n\'avez pas les droits requis pour accéder à cette ressource');
                        this.$state.go('main.home');
                    } else {
                        this.$state.go('auth.login');
                    }
                }
                return identity;
            }
        );
    }

    loginSuccess() {
        this.$state.go('main.home');
    }

}