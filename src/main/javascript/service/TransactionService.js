function TransactionService(AbstractService, TransactionDao, $log) {
    AbstractService.call(this, TransactionDao);

    this.createFromCsv = function(file) {

        var parseResult = Papa.parse(file.data.trim()).data; //TODO: Extract Papa parse to an interface
        var columnTitles = parseResult.shift();

        var transactions = [];

        if(columnTitles[0] === 'Date') {
            transactions = this.createTransactionsFromHuntington(parseResult);
        } else if(columnTitles[0] === 'Type') {
            transactions = this.createTransactionsFromChase(parseResult);
        } else {
            $log.warn('Unknown CSV structure. File Ignored.');
        }

        return this.create(transactions);
    };

    this.createTransactionsFromHuntington = function(parsedObjects) {

        var transactions = [];

        parsedObjects.forEach(function(object) {

            var description = object[2];

            //Sometimes the first description column is blank so we have to use the second
            if(description.trim().length === 0) {
                description = object[3]
            }

            transactions.push({
                date: moment(object[0], 'MM/DD/YYYY').format('YYYY-MM-DD'),
                //Change descriptions from uppercase to every word capitalized
                description: description.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();}),
                amount: parseFloat(object[4].replace(',', ''))
            });

        });

        return transactions;
    };

    this.createTransactionsFromChase = function(parsedObjects) {

        var transactions = [];

        parsedObjects.forEach(function(object) {

            transactions.push({
                date: moment(object[1], 'MM/DD/YYYY').format('YYYY-MM-DD'),
                //Change descriptions from uppercase to every word capitalized
                description: object[3].replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();}),
                amount: parseFloat(object[4].replace(',', ''))
            });

        });

        return transactions;
    };

    this.getAllInDateRange = function(beginDate, endDate) {
        return TransactionDao.getAllInDateRange(beginDate, endDate);
    };


}

angular
    .module('scamperApp')
    .service('TransactionService', TransactionService);