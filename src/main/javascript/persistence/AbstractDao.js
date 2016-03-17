function AbstractDaoFactory(Restangular, AppSettings) {

    return function AbstractDao(resourceName) {

        var restangularBase = Restangular.withConfig(function (RestangularConfigurer) {
            RestangularConfigurer.setBaseUrl(AppSettings.api.url);
        });

        this.getBase = function () {
            return restangularBase;
        };

        this.getAll = function () {
            return restangularBase.all(resourceName).getList();
        };

        this.get = function (id) {
            return restangularBase.one(resourceName, id).get();
        };

        this.delete = function (resource) {
            return resource.remove();
        };

        this.update = function (resource) {
            return resource.put();
        };

        this.create = function (resource) {
            return restangularBase.all(resourceName).post(resource);
        };
    };

}

angular
    .module('scamperApp')
    .factory('AbstractDao', AbstractDaoFactory);