describe('AbstractServiceSpec', function() {

    var dao;
    var abstractService;
    
    var dummyReturnValue;
    var dummyResource;


    beforeEach(function() {

        dummyReturnValue = {
            someProperty: 'some value'
        };

        dummyResource = {
            id: 1,
            name: 'some resource'
        };

        module('scamperApp');
        inject(function (AbstractService, AbstractDao) {

            dao = new AbstractDao('someResource');
            abstractService = new AbstractService(dao);

        });
    });

    function testAbstractServiceToDaoDelegation(serviceMethod, daoMethod, parameter) {
        spyOn(dao, daoMethod).andReturn(dummyReturnValue);
        var getResult = abstractService[serviceMethod](parameter);
        expect(getResult).toBe(dummyReturnValue);
        expect(dao[daoMethod]).toHaveBeenCalledWith(parameter);
    }

    it('delegates get requests to the DAO', function () {
        testAbstractServiceToDaoDelegation('get', 'get',  1);
    });

    it('delegates getAll requests to the DAO', function () {
        testAbstractServiceToDaoDelegation('getAll', 'getAll');
    });

    it('delegates delete requests to the DAO', function () {
        testAbstractServiceToDaoDelegation('delete', 'delete', dummyResource);
    });

    it('delegates update requests to the DAO', function () {
        testAbstractServiceToDaoDelegation('update', 'update', dummyResource);
    });

});