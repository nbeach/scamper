import angularApp from '../AngularApp';
import template from '../../html/navigation.html';

function navigationMenuCtrl($location, navigationMenuItems) {

    this.isActiveMenuItem = function (index) {
        return navigationMenuItems[index].path === $location.path();
    };

    this.hasChildren = function (item) {
        return (typeof item.children !== 'undefined' && item.children !== null && item.children.length > 0);
    };

    this.hasGlyph = function (item) {
        return typeof item.glyph !== 'undefined' && item.glyph !== null;
    };

    this.getMenuItems = function() {
        return navigationMenuItems;
    };

}

angularApp
    .controller('navigationMenuCtrl', navigationMenuCtrl)
    .directive('navigationMenu', function () {
        return {
            restrict: 'EA',
            template: template,
            scope: {},
            controllerAs: 'ctrl',
            controller: 'navigationMenuCtrl'
        }
    });
export default navigationMenuCtrl;