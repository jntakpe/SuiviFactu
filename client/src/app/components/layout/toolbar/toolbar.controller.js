export default class ToolbarController {

    constructor($mdUtil, $mdSidenav) {
        'ngInject';
        this.$mdUtil = $mdUtil;
        this.$mdSidenav = $mdSidenav;
    }

    openSidenav(navID) {
        this.$mdUtil.debounce(() => this.$mdSidenav(navID).toggle(), 300)();
    }

    toggleNotificationsTab(tab) {
        //FIXME uncomment
        // $scope.$parent.$broadcast('triSwitchNotificationTab', tab);
        this.openSidenav('notifications');
    }
}