function AccountDao(AbstractDao) {
    return new AbstractDao('account');
}

angular
    .module('scamperApp')
    .factory('AccountDao', AccountDao);