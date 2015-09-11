export default class SignupController {

    constructor(toastr, UserService) {
        'ngInject';
        this.toastr = toastr;
        this.UserService = UserService;
        this.user = {};
    }

    register() {
        this.UserService.create(this.user).then(function registered() {

        });
    }

}