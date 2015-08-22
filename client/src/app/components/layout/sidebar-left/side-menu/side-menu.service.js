export default class SideMenuService {

    constructor() {
        'ngInject';
        this.menu = [];
        this.traverse = function traverse(obj, callback, depth) {
            depth = depth === undefined ? -1 : depth;
            if (obj instanceof Array) {
                depth++;
                for (var i = 0; i < obj.length; i++) {
                    this.traverse(obj[i], callback, depth);
                }
            } else {
                callback(obj, depth);
                if (obj.children !== undefined) {
                    this.traverse(obj.children, callback, depth);
                }
            }
        }
    }

    addMenu(item) {
        this.menu.push(item);
    }

    getMenu() {
        return this.menu;
    }

    traverseMenu(callback) {
        traverse(menu, callback);
    }

    getPath() {
        var path = [];
        service.traverseMenu(item => {
            if (item.active) {
                path.push(item);
            }
        });
        return path;
    }

}