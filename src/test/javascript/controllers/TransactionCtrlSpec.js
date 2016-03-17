describe('TransactionCtrlSpec', function() {

    var mockTransactionService, mockAccounts, mockTransactions, controller, mockPromise;

    beforeEach( function() {
        module('scamperApp');

        mockTransactionService = {
            getAllInDateRange: function() {}
        };

        mockAccounts = [1, 2, 3];
        mockTransactions = [1, 2, 3];
        mockPromise = {
            then: function() {}
        };

        inject(function ($controller) {
            controller = $controller('TransactionCtrl', {TransactionService: mockTransactionService, Transactions: mockTransactions, Accounts: mockAccounts});
        });
    });

    it('loads the injected transactions', function() {
        expect(controller.transactions).toBe(mockTransactions)
    });

    it('gets transactions for the date range when it changes', function() {

        var mockPromise = {
            then: jasmine.createSpy()
        };
        var newMockTransactions = [4, 5, 6];

        spyOn(mockTransactionService, 'getAllInDateRange').andReturn(mockPromise);
        var mockBeginDate = moment('2001-01-11', 'YYYY-MM-DD');
        var mockEndDate = moment('2001-01-11', 'YYYY-MM-DD');

        controller.dateChanged(mockBeginDate, mockEndDate);

        var promiseCallback = mockPromise.then.argsForCall[0][0];
        promiseCallback(newMockTransactions);

        expect(controller.transactions).toBe(newMockTransactions)
    });


});