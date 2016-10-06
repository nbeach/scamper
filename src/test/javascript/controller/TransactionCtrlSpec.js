import TransactionCtrl from '../../../main/javascript/controller/TransactionCtrl';
import appSettings from '../../../main/javascript/AppSettings';
import moment from 'moment';

describe('TransactionCtrlSpec', function() {

    var mockTransactionService, mockAccounts, mockTransactions, controller, mockPromise, mockSettings;

    beforeEach( function() {

        mockTransactionService = {
            getAllInDateRange: function() {}
        };

        mockAccounts = [1, 2, 3];
        mockTransactions = [1, 2, 3];
        mockPromise = {
            then: function() {}
        };

        mockSettings = {
            date: {
                displayFormat: 'MM/DD/YYYY'
            },
            transaction:{
            default:
                {
                    beginDate: moment().subtract(1, 'months'),
                    endDate: moment(),
                    minimumDate: moment('2014-01-01', 'YYYY-MM-DD'),
                    maximumDate: moment().endOf('day')
                }
            }
        };

        controller = new TransactionCtrl(mockTransactionService, mockTransactions, mockAccounts, mockSettings);
    });

    it('loads the injected transactions', function() {
        expect(controller.transactions.length).toBe(mockTransactions.length);
        mockTransactions.forEach(function (value, index) {
            expect(controller.transactions[index]).toBe(value);
        });
    });

    it('gets transactions for the date range when it changes', function() {

        var mockPromise = {
            then: jasmine.createSpy()
        };
        var newMockTransactions = [4, 5, 6];

        spyOn(mockTransactionService, 'getAllInDateRange').and.returnValue(mockPromise);
        var mockBeginDate = moment('2001-01-11', 'YYYY-MM-DD');
        var mockEndDate = moment('2001-01-11', 'YYYY-MM-DD');

        controller.dateChanged(mockBeginDate, mockEndDate);

        var promiseCallback = mockPromise.then.calls.mostRecent().args[0];
        promiseCallback(newMockTransactions);

        expect(controller.transactions).toBe(newMockTransactions)
    });


});