import refMenu from './side-menu/referentiel-menu.model.js';
import bcMenu from './side-menu/boncommande-menu.model.js';

export default class SidebarLeftController {

    constructor($scope, $timeout, $mdSidenav, SideMenuService) {
        'ngInject';
        SideMenuService.addMenu(refMenu);
        SideMenuService.addMenu(bcMenu);
        this.menu = SideMenuService.getMenu();
        $scope.$on('$locationChangeSuccess', () => $timeout(() => $mdSidenav('left').close()));
    }

}