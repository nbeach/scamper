import AbstractDao from '../../../main/javascript/persistence/AbstractDao';

describe('AbstractDaoSpec', function() {

    var restangular;
    var abstractDao;

    var dummyResourceName;
    var dummyResource;
    var dummyPromise;
    var mockAppSettings;

    beforeEach( function() {

        dummyResource = {
            id: 1,
            name: 'some resource',
            remove: function() {},
            put: function() {}

        };

        dummyPromise = {
            then: function() {}
        };

        mockAppSettings = {
            api: {
                url: 'someUrl'
            }
        };
        dummyResourceName = 'someResource';

        restangular = jasmine.createSpyObj('restangular', ['withConfig']);
        restangular.withConfig.and.returnValue(restangular);

        abstractDao = new AbstractDao(mockAppSettings, restangular);
        abstractDao._resourceName = dummyResourceName;
    });


    function twoStepRestangularCallTest(testMethod, testParam, firstRestangularCall, secondRestangularCall) {

        restangular[firstRestangularCall] = jasmine.createSpy('restangular.' + firstRestangularCall);
        var mockResponse = jasmine.createSpyObj('mockResponse', [secondRestangularCall]);
        mockResponse[secondRestangularCall].and.returnValue(dummyPromise);
        restangular[firstRestangularCall].and.returnValue(mockResponse);

        var result = testMethod.call(abstractDao, testParam);

        expect(result).toBe(dummyPromise);

        if(testParam !== null && typeof testParam === 'object') {
            expect(restangular[firstRestangularCall]).toHaveBeenCalledWith(dummyResourceName);
            expect(mockResponse[secondRestangularCall]).toHaveBeenCalledWith(testParam);

        } else if(typeof testParam === 'number') {
            expect(restangular[firstRestangularCall]).toHaveBeenCalledWith(dummyResourceName, testParam);
            expect(mockResponse[secondRestangularCall]).toHaveBeenCalledWith();

        } else {
            expect(restangular[firstRestangularCall]).toHaveBeenCalledWith(dummyResourceName);
            expect(mockResponse[secondRestangularCall]).toHaveBeenCalledWith();
        }
    }

    it('gets all the resources', function() {
        twoStepRestangularCallTest(abstractDao.getAll, null, 'all', 'getList');
    });

    it('gets resources by id', function() {
        twoStepRestangularCallTest(abstractDao.get, 1, 'one', 'get');
    });

    it('creates resources', function () {
        twoStepRestangularCallTest(abstractDao.create, dummyResource, 'all', 'post');
    });


    function restangularCallOnParameterTest(testMethod, parameter, parameterMethod) {
        spyOn(parameter, parameterMethod).and.returnValue(dummyPromise);

        var result = testMethod(parameter);

        expect(result).toBe(dummyPromise);
        expect(parameter[parameterMethod]).toHaveBeenCalledWith();
    }

    it('deletes resources', function () {
        restangularCallOnParameterTest(abstractDao.delete, dummyResource, 'remove');
    });

    it('updates resources', function () {
        restangularCallOnParameterTest(abstractDao.update, dummyResource, 'put');
    })


});