export default class UserService {

    constructor(Restangular) {
        'ngInject';
        this.baseUsers = Restangular.all('utilisateurs');
    }

    create(user) {
        return this.baseUsers.post(user);
    }

    forgotPassword(email) {
        return this.baseUsers.customGET('forgotpwd', {email});
    }

    isNameAvailable(name, id) {
        return this.baseUsers.customGET('nameAvailable', {name, id});
    }

    isEmailAvailable(email, id) {
        return this.baseUsers.customGET('emailAvailable', {email, id});
    }

    resetPassword(email) {
        return this.baseUsers.customPOST({email}, 'resetPassword');
    }

    activate(activationKey) {
        return this.baseUsers.customPOST({}, 'activate/' + activationKey);
    }
}