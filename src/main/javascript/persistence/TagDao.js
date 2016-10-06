import angularApp from '../AngularApp';
import AbstractDao from './AbstractDao';

class TagDao extends AbstractDao {

    constructor(appSettings, Restangular) {
        super(appSettings, Restangular);
        this._resourceName = 'tag';
    }

}

angularApp.factory('TagDao', TagDao);
export default TagDao;