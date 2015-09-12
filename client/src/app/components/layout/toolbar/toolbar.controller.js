export default class ToolbarController {

    constructor($mdUtil, $mdSidenav, $state, AuthService, identity) {
        'ngInject';
        this.$mdUtil = $mdUtil;
        this.$mdSidenav = $mdSidenav;
        this.AuthService = AuthService;
        this.$state = $state;
        this.name = identity.nom;
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
        this.$state.go('auth.login', {logout: true});
    }
}