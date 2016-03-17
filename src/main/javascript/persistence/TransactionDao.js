function TransactionDao(AbstractDao, AppSettings) {
    var resourceName = 'transaction';
    var dao = new AbstractDao(resourceName);

    dao.getAllInDateRange = function(beginDate, endDate) {
        var formattedBeginDate = beginDate.format(AppSettings.api.dateFormat);
        var formattedEndDate = endDate.format(AppSettings.api.dateFormat);
        return dao.getBase().all(resourceName).getList({beginDate: formattedBeginDate, endDate: formattedEndDate});
    };

    return dao;
}

angular
    .module('scamperApp')
    .factory('TransactionDao', TransactionDao);