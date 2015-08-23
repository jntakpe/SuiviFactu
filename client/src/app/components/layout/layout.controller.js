export default class LayoutController {
    constructor($element, $timeout, $mdUtil, $state) {
        'ngInject';
        this.$element = $element;
        this.$timeout = $timeout;
        this.$mdUtil = $mdUtil;
        this.$state = $state;

        this.toolbarShrink = undefined;
        this.isMenuLocked = true;
        this.isMenuCollapsing = false;
        this.isHovering = false;
    }

    activateHover() {
        this.isHovering = true;
    }

    removeHover() {
        this.isHovering = false;
    }

    toggleMenuLock() {
        this.isMenuLocked = !this.isMenuLocked;
        this.isMenuCollapsing = !this.isMenuLocked;
        if (this.isMenuCollapsing) {
            this.isHovering = false;
            this.$timeout(()=> this.isMenuCollapsing = false, 400);
        }
    }

    menuClass() {
        return this.isMenuLocked ? '' : (this.isMenuCollapsing ? 'is-collapsing' :
            (this.isHovering ? 'sidebar-collapsed hover' : 'sidebar-collapsed'));
    }
}
