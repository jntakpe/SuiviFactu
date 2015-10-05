import authStateConfig from './authentification.route.js';
import UserService from './user.service.js';
import usernameUniqueDirective from './signup/username-unique.directive.js';
import emailUniqueDirective from './signup/email-unique.directive.js';
import emailSopraDirective from './signup/email-sopra.directive.js';
import samePasswordDirective from './signup/same-password.directive.js';
import emailExistDirective from './forgot/email-exist.directive.js';

export default angular
    .module('sf.authentication', [])
    .config(authStateConfig)
    .service('UserService', UserService)
    .directive('usernameUnique', usernameUniqueDirective)
    .directive('emailUnique', emailUniqueDirective)
    .directive('emailSopra', emailSopraDirective)
    .directive('samePassword', samePasswordDirective)
    .directive('emailExist', emailExistDirective);
