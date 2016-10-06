import angularApp from '../AngularApp';

class AbstractService {


    constructor(dao) {
        this._dao = dao;
    }

    getAll(limit) {
        return this._dao.getAll(limit);
    }

    get(id) {
        return this._dao.get(id);
    };

    delete(resource) {
        return this._dao.delete(resource);
    };

    update(resource) {
        return this._dao.update(resource);
    };

    create(resource) {
        return this._dao.create(resource);
    };

}

export default AbstractService;