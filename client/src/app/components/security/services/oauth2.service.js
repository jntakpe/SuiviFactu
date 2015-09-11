export default class OAuth2Service {

    constructor($http, localStorageService, baseUrl) {
        'ngInject';
        this.$http = $http;
        this.localStorageService = localStorageService;
        this.baseUrl = baseUrl;
    }

    login(credentials) {
        /* global btoa:false */
        var data = `username=${credentials.username}&password=${credentials.password}&grant_type=password&scope=read%20write&`;
        return this.$http.post(this.baseUrl + 'oauth/token', data, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'Accept': 'application/json',
                'Authorization': 'Basic ' + btoa('suivifactu' + ':' + 'suivifactusupersecuresecret')
            }
        }).then(response => {
            let expiredAt = new Date();
            expiredAt.setSeconds(expiredAt.getSeconds() + response.data.expires_in);
            response.data.expires_at = expiredAt.getTime();
            this.localStorageService.set('token', response.data);
            return response.data;
        });
    }

    logout() {
        this.$http.post(this.baseUrl + 'api/logout').then(() => this.localStorageService.clearAll());
    }

    getToken() {
        return this.localStorageService.get('token');
    }

    hasValidToken() {
        var token = this.getToken();
        return token && token.expires_at && token.expires_at > new Date().getTime();
    }
}
