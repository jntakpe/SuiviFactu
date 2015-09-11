export default class SignupController {

    constructor(toastr, AuthService) {
        'ngInject';
        this.toastr = toastr;
        this.AuthService = AuthService;
        this.user = {};
    }

    register() {
        this.AuthService.register(this.user).then(function registered() {
            console.log('Registered');
        });
    }

}