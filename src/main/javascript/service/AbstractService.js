function AbstractServiceFactory() {

    return function AbstractService(dao) {

        this.getAll = function (limit) {
            return dao.getAll(limit);
        };

        this.get = function (id) {
            return dao.get(id);
        };

        this.delete = function (resource) {
            return dao.delete(resource);
        };

        this.update = function (resource) {
            return dao.update(resource);
        };

        this.create = function (resource) {
            return dao.create(resource);
        };
    };

}

angular
    .module('scamperApp')
    .factory('AbstractService', AbstractServiceFactory);