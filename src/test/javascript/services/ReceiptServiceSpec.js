describe('ReceiptServiceSpec', function() {

    var abstractServiceCreateSpy;
    var abstractService;
    var receiptService;

    var dummyFile;
    var dummyPromise;


    beforeEach(function() {

        dummyPromise = {
            then: function () {
            }
        };

        dummyFile = {
            mimeType: 'some/type',
            data: 'data!!!!'
        };

        abstractServiceCreateSpy = jasmine.createSpy().andReturn(dummyPromise);

        abstractService = function() {
            this.create = abstractServiceCreateSpy;
        };

        module('scamperApp');
        module(function($provide) {
            $provide.value('AbstractService', abstractService);
        });
        inject(function (ReceiptService) {
            receiptService = ReceiptService;
        });
    });


    it('creates receipts from image files', function () {

        var result = receiptService.createFromImage(dummyFile);

        expect(result).toBe(dummyPromise);
        expect(abstractServiceCreateSpy).toHaveBeenCalled();
        expect(abstractServiceCreateSpy.mostRecentCall.args[0].mimeType).toBe(dummyFile.mimeType);
        expect(abstractServiceCreateSpy.mostRecentCall.args[0].file).toBe(dummyFile.data);
    });

});