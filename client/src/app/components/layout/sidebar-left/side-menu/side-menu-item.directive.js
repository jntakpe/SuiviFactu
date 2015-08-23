export default function sideMenuItemDirective() {
    'ngInject';

    return {
        restrict: 'E',
        scope: {},
        bindToController: {
            item: '='
        },
        template: '<div ng-include="sdm.itemTemplate"></div>',
        controller: SideMenuController,
        controllerAs: 'sdm'
    };
}

class SideMenuController {

    constructor($scope, $state) {
        'ngInject';
        this.$scope = $scope;
        this.$state = $state;
        this.itemTemplate = `app/components/layout/sidebar-left/side-menu/side-menu-${this.item.type}.tmpl.html`;
        this.item.url = $state.href(this.item.state);
        this.activeClass = () => this.isActive() ? 'md-hue-3' : '';
        this.openLink = () => $state.go(this.item.state, this.item.params || {});

        if (this.isActive()) {
            this.openMenu();
        }

        $scope.$on('toggleMenu', (event, item, open) => this.item.open = this.item === item ? open : false);

        $scope.$on('$locationChangeSuccess', () => {
            this.item.active = false;
            this.item.open = false;
            if (this.isActive()) {
                this.openMenu();
            }
        });

        $scope.$on('openParents', () => {
            this.item.active = true;
            this.item.open = true;
        });
    }

    toggleMenu() {
        this.$scope.$parent.$parent.$broadcast('toggleMenu', this.item, !this.item.open);
    }

    isActive() {
        return this.$state.includes(this.item.state, this.item.params || {});
    }

    openMenu() {
        this.item.active = true;
        this.item.open = true;
        this.$scope.$emit('openParents');
    }
}