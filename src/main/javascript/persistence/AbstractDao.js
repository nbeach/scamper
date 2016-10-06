class AbstractDao {

    constructor(appSettings, Restangular) {
        this._appSettings = appSettings;
        this._resourceName = this._resourceName || null;
        this._restangularBase = Restangular.withConfig(function (RestangularConfigurer) {
            RestangularConfigurer.setBaseUrl(this._appSettings.api.url);
        });
    }

    getAll() {
        return this._restangularBase.all(this._resourceName).getList();
    };

    get(id) {
        return this._restangularBase.one(this._resourceName, id).get();
    };

    delete(resource) {
        return resource.remove();
    };

    update(resource) {
        return resource.put();
    };

    create(resource) {
        return this._restangularBase.all(this._resourceName).post(resource);
    };

}

export default AbstractDao;