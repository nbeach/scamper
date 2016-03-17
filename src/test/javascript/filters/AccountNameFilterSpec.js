describe('AccountNameFilter', function() {
    var filter;

    beforeEach(module('scamperApp'));
    beforeEach(inject(function($filter) {
        filter = $filter;
    }));

    it('finds the correct account name based on the account number', function () {
        var mockAccounts = [
            {
                id: 1,
                name: 'Chase VISA'
            },
            {
                id: 2,
                name: 'Huntington Checking'
            }
        ];
        var accountId = 2;

        var result =  filter('AccountNameFilter')(accountId, mockAccounts)

        expect(result).toBe(mockAccounts[1].name);
    });

    it('returns nothing if the accountId is null', function () {
        var mockAccounts = [];
        var accountId = null;

        var result =  filter('AccountNameFilter')(accountId, mockAccounts)

        expect(result).toBe('');
    });

});