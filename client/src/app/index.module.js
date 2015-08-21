/* global toastr:false, moment:false */
import config from './index.config';
import routerConfig from './index.route';
import runBlock from './index.run';
import coreModule from './components/core/core.module.js';
import securityModule from './components/security/security.module.js';
import layoutModule from './components/layout/layout.module.js';
import homeRoute from './home/home.route.js';
import HomeController from './home/home.controller.js';

angular.module('sf', [coreModule.name, securityModule.name, layoutModule.name])
    .constant('toastr', toastr)
    .constant('moment', moment)
    .config(config)
    .config(routerConfig)
    .run(runBlock)
    .config(homeRoute)
    .controller('HomeController', HomeController);