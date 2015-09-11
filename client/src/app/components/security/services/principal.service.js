export default class PrincipalService {

    constructor($q, $http, baseUrl) {
        'ngInject';
        this.$q = $q;
        this.$http = $http;
        this.identity = null;
        this.baseUrl = baseUrl;
        this.authenticated = false;
    }

    isIdentityResolved() {
        return this.identity !== null;
    }

    isAuthenticated() {
        return this.authenticated;
    }

    isInRole(role) {
        return this.authenticated && this.identity && this.identity.roles && this.identity.roles.indexOf(role) !== -1;
    }

    isInAnyRole(roles) {
        if (!this.authenticated || !this.identity) {
            return false;
        }
        roles.forEach((role) => {
            if (this.isInRole(role)) {
                return true;
            }
        });
        return false;
    }

    authenticate(account) {
        this.identity = account;
        this.authenticated = this.identity !== null;
    }

    resolveIdentity(force) {
        var deferred = this.$q.defer();
        if (force === true) {
            this.identity = null;
        }
        if (this.isIdentityResolved()) {
            deferred.resolve(this.identity);
            return deferred.promise;
        }
        this.$http.get(this.baseUrl + 'api/account').then((response) => {
            this.identity = response.data;
            this.authenticated = true;
            deferred.resolve(this.identity);
        }).catch(() => {
            this.identity = null;
            this.authenticated = false;
            deferred.resolve(this.identity);
        });
        return deferred.promise;
    }

}