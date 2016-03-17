function appConfig($routeProvider, $locationProvider) {

    $routeProvider
        .when('/overview', {
            templateUrl: 'views/overview.html',
            controller: 'OverviewCtrl',
            controllerAs: 'ctrl'
        })
        .when('/transaction', {
            templateUrl: 'views/transaction.html',
            controller: 'TransactionCtrl',
            controllerAs: 'ctrl',
            resolve: {
                Accounts: function (AccountService) {
                    return AccountService.getAll();
                },
                //Resolve transactions beforehand to prevent page flickering
                Transactions: function (TransactionService, AppSettings) {
                    return TransactionService.getAllInDateRange(
                        AppSettings.transaction.default.beginDate,
                        AppSettings.transaction.default.endDate);
                }
            }
        })
        .when('/import', {
            templateUrl: 'views/import.html',
            controller: 'ImportCtrl',
            controllerAs: 'ctrl'
        })
        .otherwise({
            redirectTo: '/overview'
        });

    //$locationProvider.html5Mode(true);

}

angular
    .module('scamperApp', ['ngRoute', 'restangular', 'ui.bootstrap', 'objectTable'])
    .config(appConfig);

