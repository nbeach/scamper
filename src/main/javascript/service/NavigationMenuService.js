function NavigationMenuService(Restangular){
    this.get = function() {
        return Restangular.all('data/navigation.json').getList();
    };
}

angular
    .module('scamperApp')
    .service('NavigationMenuService', NavigationMenuService);