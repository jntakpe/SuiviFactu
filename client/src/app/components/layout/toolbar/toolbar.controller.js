export default class ToolbarController {

    constructor($mdUtil, $mdSidenav, AuthService) {
        'ngInject';
        this.$mdUtil = $mdUtil;
        this.$mdSidenav = $mdSidenav;
        this.AuthService = AuthService;
    }

    openSidenav(navID) {
        this.$mdUtil.debounce(() => this.$mdSidenav(navID).toggle(), 300)();
    }

    toggleNotificationsTab(tab) {
        //FIXME uncomment
        // $scope.$parent.$broadcast('triSwitchNotificationTab', tab);
        this.openSidenav('notifications');
    }

    logout() {
        this.AuthService.logout();
    }
}