export default function usernameUniqueDirective(UserService) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: link
    };

    function link(scope, element, attrs, ngModel) {
        ngModel.$asyncValidators.unique = function validate(modelValue) {
            return UserService.isNameAvailable(modelValue);
        };
    }
}