export default class LoginController {

    constructor($stateParams, toastr, AuthService) {
        'ngInject';
        this.toastr = toastr;
        this.AuthService = AuthService;
        this.user = {};
        AuthService.logout();
        if ($stateParams.logout) {
            toastr.success('Vous êtes à présent déconnecté de l\'application');
        }
    }

    login() {
        this.AuthService.login(this.user).then(() => this.AuthService.loginSuccess()).catch(() => {
            this.user = {};
            this.toastr.error('Invalid credentials');
        });
    }

}