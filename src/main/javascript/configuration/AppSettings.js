var AppSettings = {
    api: {
        url: 'http://api.scamper.local',
        dateFormat: 'YYYY-MM-DD'
    },

    date: {
        displayFormat: 'MM/DD/YYYY'
    },

    transaction:{
        default: {
            beginDate: moment().subtract(1, 'months'),
            endDate: moment(),
            minimumDate: moment('2014-01-01', 'YYYY-MM-DD'),
            maximumDate: moment().endOf('day')
        }
    },

    file: {
        image: {
            mimeTypes: ['image/png', 'image/jpeg', 'image/gif']
        },

        csv: {
            mimeTypes: ['text/csv']
        }
    }

};

angular
    .module('scamperApp')
    .value('AppSettings', AppSettings);