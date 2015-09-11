export default class UserService {

    constructor(Restangular) {
        'ngInject';
        this.baseUsers = Restangular.all('utilisateurs');
    }

    create(user) {
        return this.baseUsers.post(user);
    }

    isNameAvailable(name, id) {
        return this.baseUsers.customGET('nameAvailable', {name, id});
    }

    isEmailAvailable(email, id) {
        return this.baseUsers.customGET('emailAvailable', {email, id});
    }
}