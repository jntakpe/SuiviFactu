export default class LoginController {

    constructor($stateParams, toastr, AuthService) {
        'ngInject';
        this.$stateParams = $stateParams;
        this.toast = toastr;
        this.AuthService = AuthService;
        this.user = {};
        AuthService.logout();
    }

    login() {
        this.AuthService.login(this.user).then(() => this.AuthService.loginSuccess()).catch(() => {
            this.user = {};
            this.toastr.error('Invalid credentials');
        });
    }

}