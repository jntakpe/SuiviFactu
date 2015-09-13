export default class LoginController {

    constructor($stateParams, toastr, $timeout, AuthService, PrincipalService) {
        'ngInject';
        this.toastr = toastr;
        this.AuthService = AuthService;
        this.user = {};
        if (PrincipalService.isAuthenticated()) {
            AuthService.logout();
        }
        if ($stateParams.logout) {
            $timeout(() => toastr.info('Vous êtes à présent déconnecté de l\'application'), 1000);
        }
        if ($stateParams.register) {
            $timeout(() => toastr.success('Vous êtes bien enregistré. Vous allez recevoir un mail d\'activation du compte.'), 1000);
        }
        if ($stateParams.forgot) {
            $timeout(() => {
                toastr.success('Votre mot de passe a été réinitialisé. Vous allez recevoir un mail contenant un nouveau mot de passe');
            }, 1000);
        }
    }

    login(form) {
        this.AuthService.login(this.user)
            .then(() => this.AuthService.loginSuccess())
            .catch(() => {
                this.toastr.error('Les identifiants renseignés sont invalides');
                this.user = {};
                form.$setPristine();
                form.$setUntouched();
            });
    }

}