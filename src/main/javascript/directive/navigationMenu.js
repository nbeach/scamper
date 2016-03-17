function navigationMenuCtrl($location, NavigationMenuService) {

    var menuItems = [];

    NavigationMenuService.get().then(function(MenuItems) {
        menuItems = MenuItems;
    });

    this.isActiveMenuItem = function (index) {
        return menuItems[index].path === $location.path();
    };

    this.hasChildren = function (item) {
        return (typeof item.children !== 'undefined' && item.children !== null && item.children.length > 0);
    };

    this.hasGlyph = function (item) {
        return typeof item.glyph !== 'undefined' && item.glyph !== null;
    };

    this.getMenuItems = function() {
        return menuItems;
    };

}

angular
    .module('scamperApp')
    .controller('navigationMenuCtrl', navigationMenuCtrl)
    .directive('navigationMenu', function () {
        return {
            restrict: 'EA',
            templateUrl: 'views/navigation.html',
            scope: {},
            controllerAs: 'ctrl',
            controller: 'navigationMenuCtrl'
        }
    });