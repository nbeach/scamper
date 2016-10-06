import angularApp from '../AngularApp';
import AbstractDao from './AbstractDao';

class ReceiptDao extends AbstractDao {

    constructor(appSettings, Restangular) {
        super(appSettings, Restangular);
        this._resourceName = 'receipt';
    }

}

angularApp.factory('ReceiptDao', ReceiptDao);
export default ReceiptDao;