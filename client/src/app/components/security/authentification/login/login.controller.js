export default class LoginController {

    constructor($stateParams, toastr, AuthService, PrincipalService) {
        'ngInject';
        this.toastr = toastr;
        this.AuthService = AuthService;
        this.user = {};
        if (PrincipalService.isAuthenticated()) {
            AuthService.logout();
        }
        if ($stateParams.logout) {
            toastr.info('Vous êtes à présent déconnecté de l\'application');
        }
        if ($stateParams.register) {
            toastr.success('Vous êtes bien enregistré. Vous allez recevoir un mail d\'activation du compte.')
        }
    }

    login() {
        this.AuthService.login(this.user).then(() => this.AuthService.loginSuccess()).catch(() => {
            this.user = {};
            this.toastr.error('Invalid credentials');
        });
    }

}