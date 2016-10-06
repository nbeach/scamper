import angularApp from '../AngularApp';

function AccountNameFilter() {
    return function(accountId, accounts) {

        var accountLookup = {};
        accounts.forEach(function(account) {
            accountLookup[account.id] = account.name;
        });

        if(accountId !== null) {
            return accountLookup[accountId];
        } else {
            return "";
        }
    };
}

angularApp.filter('AccountNameFilter', AccountNameFilter);
export default AccountNameFilter;