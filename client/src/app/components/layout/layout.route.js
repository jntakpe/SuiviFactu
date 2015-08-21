import LayoutController from './layout.controller.js';
import SidebarLeftController from './sidebar-left/sidebar-left.controller.js';

export function layoutRoute($stateProvider) {
    'ngInject';
    $stateProvider.state('layout', {
        templateUrl: 'app/components/layout/layout.html',
        controller: LayoutController,
        controllerAs: 'lyt'
    });
}

export function mainRoute($stateProvider) {
    'ngInject';
    $stateProvider.state('main', {
        parent: 'layout',
        abstract: true,
        views: {
            'sidebarLeft': {
                templateUrl: 'app/components/layout/sidebar-left/sidebar-left.html',
                controller: SidebarLeftController,
                controllerAs: 'sdl'
            }
        }
    });
}
