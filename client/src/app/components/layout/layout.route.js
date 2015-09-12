import LayoutController from './layout.controller.js';
import SidebarLeftController from './sidebar-left/sidebar-left.controller.js';
import ToolbarController from './toolbar/toolbar.controller.js';

export function layoutRoute($stateProvider) {
    'ngInject';
    $stateProvider.state('layout', {
        templateUrl: 'app/components/layout/layout.html',
        controller: LayoutController,
        controllerAs: 'lyt',
        resolve: {
            identity: (PrincipalService) => PrincipalService.resolveIdentity()
        }
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
            },
            'toolbar': {
                templateUrl: 'app/components/layout/toolbar/toolbar.html',
                controller: ToolbarController,
                controllerAs: 'tlb'
            }
        }
    });
}
