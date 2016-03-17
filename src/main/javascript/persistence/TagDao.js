function TagDao(AbstractDao) {
    return new AbstractDao('tag');
}

angular
    .module('scamperApp')
    .factory('TagDao', TagDao);