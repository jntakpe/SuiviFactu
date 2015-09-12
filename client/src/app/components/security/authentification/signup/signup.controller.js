export default class SignupController {

    constructor(toastr, UserService, $state) {
        'ngInject';
        this.toastr = toastr;
        this.UserService = UserService;
        this.$state = $state;
        this.user = {};
    }

    register() {
        this.UserService.create(this.user).then(() => {
            this.$state.go('auth.login', {register: true});
        });
    }

}