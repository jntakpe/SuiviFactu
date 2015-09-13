export default class ForgotController {

    constructor(UserService, $state, toastr) {
        'ngInject';
        this.UserService = UserService;
        this.$state = $state;
        this.toastr = toastr;
        this.user = {};
    }

    resetPwd() {
        this.UserService.resetPassword(this.user.email)
            .then(() => this.$state.go('auth.login', {forgot: true}))
            .catch((response) => this.toastr.error('Erreur lors de l\'envoi du mail de réinitialisation'));
    }
}