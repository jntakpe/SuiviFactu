export default class SignInCtrl {

    constructor($stateParams, toastr, AuthService) {
        'ngInject';
        this.$stateParams = $stateParams;
        this.toast = toastr;
        this.AuthService = AuthService;
        this.user = {};
        AuthService.logout();
    }

    login() {
        this.AuthService.login({
            username: this.user.username,
            password: this.user.password
        }).then(() => this.AuthService.loginSuccess()).catch(() => {
            this.user = {};
            toastr.error('Invalid credentials');
        });
    }

}