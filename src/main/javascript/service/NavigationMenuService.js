import angularApp from '../AngularApp';

function NavigationMenuService(Restangular){
    this.get = function() {
        return Restangular.all('data/navigation.json').getList();
    };
}

angularApp.service('NavigationMenuService', NavigationMenuService);
export default NavigationMenuService;