export default class SidebarLeftController {

    constructor($scope, $timeout, $mdSidenav) {
        'ngInject';

        $scope.$on('$locationChangeSuccess', () => $timeout(() => $mdSidenav('left').close()));
    }
}