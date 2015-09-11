export default function samePasswordDirective() {
    return {
        restrict: 'A',
        require: 'ngModel',
        scope: {
            samePassword: '='
        },
        link: function (scope, element, attrs, ngModel) {
            ngModel.$viewChangeListeners.push(function onValueChange() {
                if (scope.samePassword.$modelValue) {
                    var valid = scope.samePassword.$modelValue === ngModel.$modelValue;
                    ngModel.$setValidity('samePassword', valid);
                    scope.samePassword.$setValidity('samePassword', valid);
                }
            });
        }
    };
}