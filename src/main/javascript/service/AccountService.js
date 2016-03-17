function AccountService(AbstractService, AccountDao) {
    AbstractService.call(this, AccountDao);
}

angular
    .module('scamperApp')
    .service('AccountService', AccountService);