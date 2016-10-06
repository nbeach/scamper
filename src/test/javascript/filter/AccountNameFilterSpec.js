import AccountNameFilter from '../../../main/javascript/filter/AccountNameFilter';

describe('AccountNameFilter', function() {
    var filter;

    beforeEach(function() {
        filter = AccountNameFilter();
    });

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

        var result =  filter(accountId, mockAccounts)

        expect(result).toBe(mockAccounts[1].name);
    });

    it('returns nothing if the accountId is null', function () {
        var mockAccounts = [];
        var accountId = null;

        var result =  filter(accountId, mockAccounts)

        expect(result).toBe('');
    });

});