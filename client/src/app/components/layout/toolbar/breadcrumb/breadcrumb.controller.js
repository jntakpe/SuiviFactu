(function () {
    'use strict';

    angular.module('parc.layout').controller('BreadcrumbCtrl', BreadcrumbCtrl);

    function BreadcrumbCtrl($scope, breadcrumbService) {
        var vm = this;

        $scope.$on('$stateChangeSuccess', updateBreadcrumb);

        function updateBreadcrumb() {
            vm.infos = breadcrumbService.infos();
        }

    }

})();