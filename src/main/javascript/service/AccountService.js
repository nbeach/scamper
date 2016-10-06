import angularApp from '../AngularApp';
import AbstractService from './AccountService';

class AccountService extends AbstractService {
    constructor(AccountDao) {
        super(AccountDao);
    }
}

angularApp.service('AccountService', AccountService);
export default AccountService;