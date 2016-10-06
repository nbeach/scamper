import angularApp from '../AngularApp';
import AbstractService from './AccountService';

class TagService extends AbstractService {
    constructor(TagDao) {
        super(TagDao);
    }
}

angularApp.service('TagService', TagService);
export default TagService;