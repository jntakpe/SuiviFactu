export default class BreadcrumbService {

    constructor($state) {
        'ngInject';
        this.$state = $state;
        this.resolveParents = function resolveParents(stateNames) {
            var breadcrumb = [];
            stateNames.forEach((name) => {
                breadcrumb.push({
                    title: $state.get(name).data.title,
                    name: name
                });
            });
            return breadcrumb;
        };
    }

    resolveBreadcrumb() {
        return {
            title: this.$state.$current.data.title,
            parents: this.resolveParents(this.$state.$current.data.breadcrumb || [])
        };
    }
}