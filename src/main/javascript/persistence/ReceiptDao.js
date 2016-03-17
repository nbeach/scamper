function ReceiptDao(AbstractDao) {
    return new AbstractDao('receipt');
}

angular
    .module('scamperApp')
    .factory('ReceiptDao', ReceiptDao);