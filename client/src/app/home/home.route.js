export default function homeRoute($stateProvider) {
    'ngInject';
    $stateProvider.state('main.home', {
        url: '/',
        templateUrl: 'app/home/home.html',
        controller: 'HomeController',
        controllerAs: 'home'
    });
}