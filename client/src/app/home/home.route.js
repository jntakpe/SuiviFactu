export default function homeRoute($stateProvider) {
    'ngInject';
    $stateProvider.state('layout.home', {
        url: '/',
        templateUrl: 'app/home/home.html',
        controller: 'HomeController',
        controllerAs: 'home'
    });
}