import TransactionDao from '../../../main/javascript/persistence/TransactionDao';
import moment from 'moment';

describe('TransactionDaoSpec', function() {
    var abstractDao;
    var abstractDaoGetBaseSpy;
    var transactionDao;

    var dummyPromise;
    var mockRestangularObject;
    var mockAppSettings;
    var restangular;

    beforeEach(function() {

        dummyPromise = {
            then: function () {
            }
        };

        mockRestangularObject = {
            all: function () {},
            getList: function () {}
        };

        mockAppSettings = {
            api: {
                dateFormat: 'YYYY-MM-DD'
            }
        };

        abstractDaoGetBaseSpy = jasmine.createSpy().and.returnValue(restangular);

        abstractDao = function() {
            this.getBase = abstractDaoGetBaseSpy;
        };

        restangular = jasmine.createSpyObj('restangular', ['withConfig', 'all', 'getList']);
        restangular.withConfig.and.returnValue(restangular);

        transactionDao = new TransactionDao(mockAppSettings, restangular);
        transactionDao._resourceName = 'transaction';
    });


    it('gets all the transactions within a date range', function () {
        var dummyStartDate = moment().subtract(1, 'months');
        var dummyEndDate = moment();

        restangular.all.and.returnValue(restangular);
        restangular.getList.and.returnValue(dummyPromise);

        var result = transactionDao.getAllInDateRange(dummyStartDate, dummyEndDate);

        expect(result).toBe(dummyPromise);
        expect(restangular.all).toHaveBeenCalledWith('transaction');
        expect(restangular.getList).toHaveBeenCalledWith({
            beginDate: dummyStartDate.format(mockAppSettings.api.dateFormat),
            endDate: dummyEndDate.format(mockAppSettings.api.dateFormat)
        });
    });


});