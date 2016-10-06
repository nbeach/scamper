import angularApp from '../AngularApp';
import AbstractDao from './AbstractDao';

class TransactionDao extends AbstractDao {

    constructor(appSettings, Restangular) {
        super(appSettings, Restangular);
        this._resourceName = 'tag';
    }

    getAllInDateRange(beginDate, endDate) {
        var formattedBeginDate = beginDate.format(this._appSettings.api.dateFormat);
        var formattedEndDate = endDate.format(this._appSettings.api.dateFormat);
        return this._restangularBase.all(this._resourceName).getList({beginDate: formattedBeginDate, endDate: formattedEndDate});
    };

}

angularApp.factory('TransactionDao', TransactionDao);
export default TransactionDao;