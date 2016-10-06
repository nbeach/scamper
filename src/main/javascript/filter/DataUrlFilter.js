import angularApp from '../AngularApp';

function DataUrlFilter() {
    return function(data, mimeType) {
       return 'data:' + mimeType + ';base64,' + data;
    };
}

angularApp.filter('DataUrlFilter', DataUrlFilter);
export default DataUrlFilter;