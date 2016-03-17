function ReceiptService(AbstractService, ReceiptDao) {
    AbstractService.call(this, ReceiptDao);

    this.createFromImage = function(file) {
        var receipt = {
            mimeType: file.mimeType,
            file: file.data
        };
        return this.create(receipt);
    };
}

angular
    .module('scamperApp')
    .service('ReceiptService', ReceiptService);