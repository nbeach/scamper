// This prevents warnings about try to load angular more than once in tests. The testing framework runs the content of this
// file multiple times without resetting the window object. In normal operation the contents of this file are only
// executed once thus the property bootstrap won't already be defined.
if(typeof window.angular !== 'undefined') { delete window.angular; }

import angular from 'angular';
import ngRoute from 'angular-route';
import 'restangular';
import 'angular-bootstrap';

import overviewTemplate from '../html/overview.html';
import transactionTemplate from '../html/transaction.html';
import importTemplate from '../html/import.html';

function appConfig($routeProvider, $locationProvider) {

    $routeProvider
        .when('/overview', {
            template: overviewTemplate,
            controller: 'OverviewCtrl',
            controllerAs: 'ctrl'
        })
        .when('/transaction', {
            template: transactionTemplate,
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
            template: importTemplate,
            controller: 'ImportCtrl',
            controllerAs: 'ctrl'
        })
        .otherwise({
            redirectTo: '/overview'
        });

    //$locationProvider.html5Mode(true);

}

let app = angular.module('scamperApp', [ngRoute, 'restangular', 'ui.bootstrap', 'objectTable']);
app.config(appConfig);

export default app;

