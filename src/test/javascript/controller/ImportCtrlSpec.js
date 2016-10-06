import ImportCtrl from '../../../main/javascript/controller/ImportCtrl';
import appSettings from '../../../main/javascript/AppSettings';

describe('ImportCtrlSpec', function() {

    var mockTransactionService, mockReceiptService, controller, mockPromise;

    beforeEach( function() {

        mockTransactionService = {
            createFromCsv: function() {}
        };

        mockReceiptService = {
            createFromImage: function() {}
        };

        mockPromise = {
            then: function() {}
        };

        controller = new ImportCtrl(mockTransactionService, mockReceiptService, appSettings);
    });


    it('can tell if a file is an image', function() {
        var result = controller.isImage({ mimeType: 'image/png' });
        expect(result).toBe(true);
    });

    it('can tell if a file is not an image', function() {
        var result = controller.isImage({ mimeType: 'audio/mid' });
        expect(result).toBe(false);
    });

    it('can tell if a file is CSV', function() {
        var result = controller.isCsv({ mimeType: 'text/csv' });
        expect(result).toBe(true);
    });

    it('can tell if a file is not a CSV', function() {
        var result = controller.isCsv({ mimeType: 'audio/mid' });
        expect(result).toBe(false);
    });


    function genericCreationTest(service, method, mimeType) {

        var mockFile = {
            name: 'filename',
            mimeType: mimeType
        };

        spyOn(service, method).and.returnValue(mockPromise);
        controller.fileLoaded(mockFile);
        expect(service[method]).toHaveBeenCalledWith(mockFile);
        expect(mockFile.uploadStatus).toBe(controller.saveStatus().IN_PROGRESS);

        return mockFile;
    }

    it('creates transactions from loaded CSV files', function() {
        genericCreationTest(mockTransactionService, 'createFromCsv', 'text/csv');
    });

    it('creates receipts from loaded image files', function() {

        genericCreationTest(mockReceiptService, 'createFromImage', 'image/png');

    });

    it('sets the upload status when saving a file', function() {
        spyOn(mockPromise, 'then');
        var mockFile = genericCreationTest(mockReceiptService, 'createFromImage', 'image/png');
        expect(mockFile.uploadStatus).toBe(controller.saveStatus().IN_PROGRESS);
    });

    it('sets the upload status to complete when the upload succeeds', function() {
        spyOn(mockPromise, 'then');
        var mockFile = genericCreationTest(mockReceiptService, 'createFromImage', 'image/png');

        var successCallback = mockPromise.then.calls.mostRecent().args[0];
        successCallback();
        expect(mockFile.uploadStatus).toBe(controller.saveStatus().COMPLETE);
    });

    it('sets the upload status to failed when the upload fails', function() {
        spyOn(mockPromise, 'then');
        var mockFile = genericCreationTest(mockReceiptService, 'createFromImage', 'image/png');

        var failureCallback = mockPromise.then.calls.mostRecent().args[1];
        failureCallback();
        expect(mockFile.uploadStatus).toBe(controller.saveStatus().FAILED);
    });

});