export default class SidebarLeftController {

    constructor($scope, $timeout, $mdSidenav, SideMenuService) {
        'ngInject';
        SideMenuService.addMenu({
            name: 'MENU.CHARTS.CHARTS',
            icon: 'insert_chart',
            type: 'dropdown',
            priority: 5.1,
            children: [{
                name: 'MENU.CHARTS.GOOGLE',
                type: 'dropdown',
                children: [{
                    name: 'MENU.CHARTS.BAR',
                    state: 'admin-panel.default.charts-google-bar',
                    type: 'link',
                }, {
                    name: 'MENU.CHARTS.SCATTER',
                    state: 'admin-panel.default.charts-google-scatter',
                    type: 'link',
                }, {
                    name: 'MENU.CHARTS.LINE',
                    state: 'admin-panel.default.charts-google-line',
                    type: 'link',
                }]
            }, {
                name: 'MENU.CHARTS.CHARTJS',
                type: 'dropdown',
                children: [{
                    name: 'MENU.CHARTS.BAR',
                    state: 'admin-panel.default.charts-chartjs-bar',
                    type: 'link',
                }, {
                    name: 'MENU.CHARTS.LINE',
                    state: 'admin-panel.default.charts-chartjs-line',
                    type: 'link',
                }, {
                    name: 'MENU.CHARTS.PIE',
                    state: 'admin-panel.default.charts-chartjs-pie',
                    type: 'link',
                }, {
                    name: 'MENU.CHARTS.TICKER',
                    state: 'admin-panel.default.charts-chartjs-ticker',
                    type: 'link',
                }]
            }]
        });
        this.menu = SideMenuService.getMenu();
        $scope.$on('$locationChangeSuccess', () => $timeout(() => $mdSidenav('left').close()));
    }
}