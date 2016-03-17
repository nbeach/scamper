describe('TransactionServiceSpec', function() {

    var abstractServiceCreateSpy;
    var abstractServiceGetAllInDateRangeSpy;
    var abstractService
    var transactionDao;
    var transactionService;

    var dummyFile;
    var dummyPromise;
    var mockParsedChaseObject;
    var mockParsedHuntingtonObject;

    beforeEach(function() {

        dummyPromise = {
            then: function () {
            }
        };

        dummyFile = {
            mimeType: 'some/type',
            data: 'data!!!!'
        };

        mockParsedChaseObject = { data: [
            ['Type', 'Trans Date', 'Post Date', 'Description', 'Amount'],
            ['SALE', '01/11/2001', '12/26/2014', 'description1', '-1,000.00'],
            ['SALE', '02/22/2002', '12/26/2014', 'description2', '-2,000.00']
        ]};

        mockParsedHuntingtonObject = { data: [
            ['Date', 'Reference Number', 'Payee Name', 'Memo', 'Amount', 'Category Name'],
            ['01/11/2001', '0', 'payee1', 'memo1', '-1,000.00',''],
            ['02/22/2002', '0', '', 'memo2', '-2,000.00','']
        ]};

        abstractServiceCreateSpy = jasmine.createSpy().andReturn(dummyPromise);
        abstractService = function() {
            this.create = abstractServiceCreateSpy;
        };

        abstractServiceGetAllInDateRangeSpy = jasmine.createSpy().andReturn(dummyPromise);
        transactionDao = {
            getAllInDateRange: abstractServiceGetAllInDateRangeSpy
        };

        module('scamperApp');
        module(function($provide) {
            $provide.value('TransactionDao', transactionDao);
            $provide.value('AbstractService', abstractService);
        });

        inject(function (TransactionService) {
            transactionService = TransactionService;
        });
    });

    function genericCreateFromCsvTest(transactionCreationMethod, mockParsedCSVObject) {

        var mockCSVFile = {
            data: 'csv data'
        };

        var mockTransactions = 'mockTransactions';

        spyOn(Papa, 'parse').andReturn(mockParsedCSVObject);
        spyOn(transactionService, transactionCreationMethod).andReturn(mockTransactions);

        var result = transactionService.createFromCsv(mockCSVFile);

        expect(result).toBe(dummyPromise);
        expect(Papa.parse).toHaveBeenCalledWith(mockCSVFile.data);
        expect(transactionService[transactionCreationMethod]).toHaveBeenCalledWith(mockParsedCSVObject.data);
        expect(abstractServiceCreateSpy).toHaveBeenCalledWith(mockTransactions);
    }


    it('identifies Huntington CSV files and creates transactions from them', function () {
        genericCreateFromCsvTest('createTransactionsFromHuntington', mockParsedHuntingtonObject);
    });

    it('identifies Chase CSV files and creates transactions from them', function () {
        genericCreateFromCsvTest('createTransactionsFromChase', mockParsedChaseObject);
    });


    it('maps parsed Huntington transactions to objects', function () {
        mockParsedHuntingtonObject.data.shift(); //Remove the column titles

        var transactions = transactionService.createTransactionsFromHuntington(mockParsedHuntingtonObject.data);

        expect(transactions[0].amount).toBe(-1000);
        expect(transactions[0].description).toBe('Payee1');
        expect(transactions[0].date).toBe('2001-01-11');

        expect(transactions[1].amount).toBe(-2000);
        expect(transactions[1].description).toBe('Memo2');
        expect(transactions[1].date).toBe('2002-02-22') ;
    });

    it('maps parsed Chase transactions to objects', function () {
        mockParsedChaseObject.data.shift(); //Remove the column titles

        var transactions = transactionService.createTransactionsFromChase(mockParsedChaseObject.data);

        expect(transactions[0].amount).toBe(-1000);
        expect(transactions[0].description).toBe('Description1');
        expect(transactions[0].date).toBe('2001-01-11');

        expect(transactions[1].amount).toBe(-2000);
        expect(transactions[1].description).toBe('Description2');
        expect(transactions[1].date).toBe('2002-02-22') ;
    });

    it('gets all of the transaction is a date range', function () {
        var dummyStartDate = 'startDate';
        var dummyEndDate = 'endDate';

        var result = transactionService.getAllInDateRange(dummyStartDate, dummyEndDate);

        expect(result).toBe(dummyPromise);
        expect(abstractServiceGetAllInDateRangeSpy).toHaveBeenCalledWith(dummyStartDate, dummyEndDate);
    });

});