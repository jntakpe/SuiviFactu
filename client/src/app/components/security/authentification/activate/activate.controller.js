export default class ActivateController {

    constructor(UserService, $stateParams, toastr) {
        'ngInject';
        this.status = 'pending';
        UserService.activate($stateParams.key).then(() => {
            this.status = 'activated';
        }).catch(() => {
            this.status = 'error';
            toastr.error('Erreur lors de l\'activation du compte');
        });
    }
}