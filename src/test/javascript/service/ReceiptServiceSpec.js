import ReceiptService from '../../../main/javascript/service/ReceiptService';

describe('ReceiptServiceSpec', function() {

    var abstractServiceCreateSpy;
    var abstractService;
    var receiptService;

    var dummyFile;
    var dummyPromise;
    var dao;


    beforeEach(function() {

        dummyPromise = {
            then: function () {
            }
        };

        dummyFile = {
            mimeType: 'some/type',
            data: 'data!!!!'
        };


        abstractService = function() {
            this.create = abstractServiceCreateSpy;
        };

        dao = jasmine.createSpyObj('receiptDao', ['create']);
        dao.create.and.returnValue(dummyPromise);

        receiptService = new ReceiptService(dao);
    });


    it('creates receipts from image files', function () {

        var result = receiptService.createFromImage(dummyFile);

        expect(result).toBe(dummyPromise);
        expect(dao.create).toHaveBeenCalled();
        expect(dao.create.calls.mostRecent().args[0].mimeType).toBe(dummyFile.mimeType);
        expect(dao.create.calls.mostRecent().args[0].file).toBe(dummyFile.data);
    });

});