import angularApp from '../AngularApp';
import AbstractDao from './AbstractDao';

class AccountDao extends AbstractDao {

    constructor(appSettings, Restangular) {
        super(appSettings, Restangular);
        this._resourceName = 'account';
    }

}

angularApp.factory('AccountDao', AccountDao);
export default AccountDao;