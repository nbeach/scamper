function fileReaderCtrl($scope, $element, FileReaderService) {

    function stopEvent(event) {
        if(event.preventDefault) { event.preventDefault(); }
        if(event.stopPropagation) { event.stopPropagation(); }
    }

    function filesDropped(event) {
        stopEvent(event);
        FileReaderService.readFilesFromEvent(event, $scope.onLoad);
    }

    //Setup handler for when the file is dropped
    $element.get(0).addEventListener("drop", filesDropped);

    //Setup other drag events to be stopped so they don't interfere with the drop
    $element.on("dragover", stopEvent);
    $element.on("dragleave", stopEvent);
}

angular
    .module('scamperApp')
    .controller('fileDropCtrl', fileReaderCtrl)
    .directive('fileDrop', function() {
        return {
            restrict: 'EA',
            scope: {
                onLoad: '='
            },
            controllerAs: 'ctrl',
            controller: 'fileDropCtrl'
        };
    });