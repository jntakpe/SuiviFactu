/// <reference path="../../.tmp/typings/tsd.d.ts" />

module sf {
    'use strict';

    export class Config {
        /** @ngInject */
        constructor(toastr: Toastr) {
            toastr.options.timeOut = 3000;
            toastr.options.positionClass = 'toast-top-right';
            toastr.options.preventDuplicates = true;
            toastr.options.progressBar = true;
        }

    }
}
