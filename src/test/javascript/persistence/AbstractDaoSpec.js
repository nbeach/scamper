describe('AbstractDaoSpec', function() {

    var restangular;
    var abstractDao;

    var dummyResourceName;
    var dummyResource;
    var dummyPromise;


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

        module('scamperApp');
        inject(function (Restangular, AbstractDao) {


            restangular = Restangular;
            dummyResourceName = 'someResource';

            spyOn(restangular, 'withConfig').andReturn(restangular);
            abstractDao = new AbstractDao(dummyResourceName);
        });
    });


    function twoStepRestangularCallTest(testMethod, testParam, firstRestangularCall, secondRestangularCall) {

        var mockResponse = {};
        mockResponse[secondRestangularCall] = function(){};

        spyOn(restangular, firstRestangularCall).andReturn(mockResponse);
        spyOn(mockResponse, secondRestangularCall).andReturn(dummyPromise);

        var result = testMethod(testParam);

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
        spyOn(parameter, parameterMethod).andReturn(dummyPromise);

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