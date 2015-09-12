import HomeController from './home.controller.js';

export default function homeRoute($stateProvider) {
    'ngInject';
    $stateProvider.state('main.home', {
        url: '/',
        data: {
            title: 'Home',
            roles: ['ROLE_USER']
        },
        views: {
            'content@layout': {
                templateUrl: 'app/home/home.html',
                controller: HomeController,
                controllerAs: 'hom'
            }
        }
    });
}