import {layoutRoute, mainRoute} from './layout.route.js';
import sideMenuItemDirective from './sidebar-left/side-menu/side-menu-item.directive.js';
import SideMenuService from './sidebar-left/side-menu/side-menu.service.js';
import breadcrumbDirective from './toolbar/breadcrumb/breadcrumb.directive.js';
import BreadcrumbService from './toolbar/breadcrumb/breadcrumb.service.js';

export default angular.module('sf.layout', [])
    .config(layoutRoute)
    .config(mainRoute)
    .directive('sideMenuItem', sideMenuItemDirective)
    .directive('breadcrumb', breadcrumbDirective)
    .service('BreadcrumbService', BreadcrumbService)
    .service('SideMenuService', SideMenuService);
