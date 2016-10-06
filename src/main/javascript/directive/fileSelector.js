import angularApp from '../AngularApp';

function fileSelectorDirectiveCtrl($scope, $element, FileReaderService) {

    function readFiles(event) {
       FileReaderService.readFilesFromEvent(event, $scope.onLoad);
    }

    //Setup handler for when the file selector changes
    $element.on('change', readFiles);

}

angularApp
    .controller('fileSelectorCtrl', fileSelectorDirectiveCtrl)
    .directive('fileSelector', function() {
        return {
            restrict: 'EA',
            scope: {
                onLoad: '='
            },
            controllerAs: 'ctrl',
            controller: 'fileSelectorCtrl'
        };
    });

export default fileSelectorDirectiveCtrl;