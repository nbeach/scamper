function TagService(AbstractService, TagDao) {
    AbstractService.call(this, TagDao);
}

angular
    .module('scamperApp')
    .service('TagService', TagService);