describe('TransactionDaoSpec', function() {
    var abstractDao;
    var abstractDaoGetBaseSpy;
    var transactionDao;

    var dummyPromise;
    var mockRestangularObject;
    var mockAppSettings;

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

        abstractDaoGetBaseSpy = jasmine.createSpy().andReturn(mockRestangularObject);

        abstractDao = function() {
            this.getBase = abstractDaoGetBaseSpy;
        };

        module('scamperApp');
        module(function($provide) {
            $provide.value('AbstractDao', abstractDao);
            $provide.value('AppSettings', mockAppSettings);
        });
        inject(function (TransactionDao) {
            transactionDao = TransactionDao;
        });
    });


    it('gets all the transactions within a date range', function () {
        var dummyStartDate = moment().subtract(1, 'months');
        var dummyEndDate = moment();

        spyOn(mockRestangularObject, 'all').andReturn(mockRestangularObject);
        spyOn(mockRestangularObject, 'getList').andReturn(dummyPromise);

        var result = transactionDao.getAllInDateRange(dummyStartDate, dummyEndDate);

        expect(result).toBe(dummyPromise);
        expect(mockRestangularObject.all).toHaveBeenCalledWith('transaction')
        expect(mockRestangularObject.getList).toHaveBeenCalledWith({
            beginDate: dummyStartDate.format(mockAppSettings.api.dateFormat),
            endDate: dummyEndDate.format(mockAppSettings.api.dateFormat)
        });
    });


});