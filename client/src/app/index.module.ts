/// <reference path="../../.tmp/typings/tsd.d.ts" />


/// <reference path="index.route.ts" />

/// <reference path="index.config.ts" />
/// <reference path="index.run.ts" />
/// <reference path="main/main.controller.ts" />
/// <reference path="../app/components/navbar/navbar.directive.ts" />
/// <reference path="../app/components/webDevTec/webDevTec.service.ts" />
/// <reference path="../app/components/githubContributor/githubContributor.service.ts" />

module sf {
    'use strict';

    angular.module('sf', ['sf.core', 'sf.security'])
        .config(Config)
        .config(RouterConfig)
        .run(RunBlock)
        .constant('toastr', toastr)
        .constant('moment', moment)

        .service('githubContributor', GithubContributor)
        .service('webDevTec', WebDevTecService)
        .controller('MainController', MainController)
        .directive('acmeNavbar', acmeNavbar);
}
