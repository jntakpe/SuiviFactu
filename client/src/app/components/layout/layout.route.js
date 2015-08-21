import LayoutController from './layout.controller.js';

export default function layoutRoute($stateProvider) {
    'ngInject';
    $stateProvider.state('layout', {
        templateUrl: 'app/components/layout/layout.html',
        controller: LayoutController,
        controllerAs: 'lyt'
    });
}