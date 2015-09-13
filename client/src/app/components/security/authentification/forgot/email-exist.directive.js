export default function emailExistDirective(UserService, $q) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: link
    };

    function link(scope, element, attrs, ngModel) {
        ngModel.$asyncValidators.exist = function validate(modelValue) {
            var deferred = $q.defer();
            UserService.isEmailAvailable(modelValue)
                .then(() => deferred.reject('L\'adresse mail n\'existe pas'))
                .catch(()=> deferred.resolve('L\'adresse mail existe'));
            return deferred.promise;
        };
    }
}