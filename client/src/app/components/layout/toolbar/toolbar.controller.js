export default class ToolbarController {

    constructor($mdUtil, $mdSidenav) {
        'ngInject';
        this.$mdUtil = $mdUtil;
        this.$mdSidenav = $mdSidenav;
    }

    openSidenavLeft(navID) {
        this.$mdUtil.debounce(() => this.$mdSidenav(navID).toggle(), 300)();
    }
}