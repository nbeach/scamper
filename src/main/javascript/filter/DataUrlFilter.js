function DataUrlFilter() {
    return function(data, mimeType) {
       return 'data:' + mimeType + ';base64,' + data;
    };
}

angular
    .module('scamperApp')
    .filter('DataUrlFilter', DataUrlFilter);