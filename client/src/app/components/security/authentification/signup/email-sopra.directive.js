export default function emailSopraDirective(UserService) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: link
    };

    function link(scope, element, attrs, ngModel) {
        ngModel.$validators.sopra = function validate(modelValue) {
            return new RegExp('@soprasteria.com$').test(modelValue);
        };
    }
}