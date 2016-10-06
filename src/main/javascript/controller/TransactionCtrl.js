import angularApp from '../AngularApp';
import template from '../../html/transaction.html';

function TransactionCtrl(TransactionService, Accounts, Transactions, appSettings) {
    var self = this;

    this.transactions = Transactions;
    this.filteredTransactions = [];
    this.sortedTransactions = [];
    this.currentPage = [];

    function setTransactions(transactions) {
        self.transactions = transactions;
    }

    this.dateChanged = function(beginDate, endDate) {
        TransactionService.getAllInDateRange(beginDate, endDate).then(setTransactions);
    };

    this.columns = [
        {
            title: 'Date',
            property: 'date',
            type: 'date',
            filter: {name:'date', param:'MM/dd/yyyy'}
        },{
            title: 'Account',
            property: 'accountId',
            type: 'number',
            filter: {name: 'AccountNameFilter', param: Accounts}
        },{
            title: 'Description',
            property: 'description',
            type: 'string'
        },{
            title: 'Amount',
            property: 'amount',
            type: 'number',
            filter: {name: 'currency'}
        }
    ];

    this.paginationConfig = {
        rowsPerPage: 10
    };

    this.sorterConfig = {
        sortColumn: this.columns[0],
        sortDescending: true
    };

    this.dateRangerPickerConfig = {
        initialBeginDate: appSettings.transaction.default.beginDate,
        initialEndDate: appSettings.transaction.default.endDate,
        minimumDate: appSettings.transaction.default.minimumDate,
        maximumDate: appSettings.transaction.default.maximumDate,
        displayFormat: appSettings.date.displayFormat,
        initialView: 'months',
        dateChanged: this.dateChanged
    };

}

angularApp.controller('TransactionCtrl', TransactionCtrl);
export default TransactionCtrl;