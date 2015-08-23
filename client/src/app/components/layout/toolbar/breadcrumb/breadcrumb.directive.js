export default function breadcrumbDirective() {
    'ngInject';

    return {
        restrict: 'E',
        scope: {},
        templateUrl: 'app/components/layout/toolbar/breadcrumb/breadcrumb.html',
        controller: BreadcrumbController,
        controllerAs: 'brd'
    };
}

class BreadcrumbController {

    constructor($scope, BreadcrumbService) {
        'ngInject';
        var updateBreadCrumb = () => this.infos = BreadcrumbService.resolveBreadcrumb();
        updateBreadCrumb();
        $scope.$on('$stateChangeSuccess', () => updateBreadCrumb());
    }

}