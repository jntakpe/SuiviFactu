import {layoutRoute, mainRoute} from './layout.route.js';

export default angular.module('sf.layout', [])
    .config(layoutRoute)
    .config(mainRoute);