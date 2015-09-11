export default class SignupController {

    constructor(toastr, AuthService) {
        'ngInject';
        this.toastr = toastr;
        this.AuthService = AuthService;
        this.user = {};
    }

    register() {
        this.AuthService.login(this.user).then(() => this.AuthService.loginSuccess()).catch(() => {
            this.user = {};
            this.toastr.error('Invalid credentials');
        });
    }

}