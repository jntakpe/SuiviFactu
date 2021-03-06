/* global toastr:false, moment:false */
import config from './index.config.js';
import routerConfig from './index.route.js';
import themingConfig from './index.theme.js';
import runBlock from './index.run.js';
import coreModule from './components/core/core.module.js';
import securityModule from './components/security/security.module.js';
import layoutModule from './components/layout/layout.module.js';
import authModule from './authentification/authentification.module.js';
import homeRoute from './home/home.route.js';

angular.module('sf', [coreModule.name, securityModule.name, layoutModule.name, authModule.name])
    .constant('toastr', toastr)
    .constant('moment', moment)
    .constant('baseUrl', '/suivifactu')
    .run(runBlock)
    .config(config)
    .config(routerConfig)
    .config(themingConfig)
    .config(homeRoute);