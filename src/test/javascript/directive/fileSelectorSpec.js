//import controller from '../../../main/javascript/directive/fileSelector';
//
//describe('fileSelectorSpec', function() {
//
//    var mockElements;
//    var mockFileReaderService;
//    var mockScope;
//
//    beforeEach(function() {
//
//        mockElements = {
//            on: function() {}
//        };
//
//        mockFileReaderService = {
//            readFilesFromEvent: function() {}
//        };
//
//        mockScope = {
//            onLoad: function() {}
//        };
//
//        module('scamperApp');
//
//
//    });
//
//    function injectAndInitController() {
//        var fileSelectorCtrl;
//
//        inject(function ($controller) {
//            fileSelectorCtrl = $controller('fileSelectorCtrl', {$scope: mockScope, $element: mockElements, FileReaderService: mockFileReaderService});
//        });
//
//        return fileSelectorCtrl;
//    }
//
//
//    it('delegates file reading to the file reader', function () {
//        spyOn(mockElements, 'on');
//        spyOn(mockFileReaderService, 'readFilesFromEvent');
//
//        injectAndInitController();
//
//        var changeFunction = mockElements.on.argsForCall[0][1];
//        var fakeEvent = 'not an event';
//        changeFunction(fakeEvent, mockScope.onLoad);
//
//        expect(mockFileReaderService.readFilesFromEvent).toHaveBeenCalledWith(fakeEvent, mockScope.onLoad);
//
//    });
//
//    it('sets up an event handler to read the file when it changes', function () {
//        spyOn(mockElements, 'on');
//
//        injectAndInitController();
//
//        expect(mockElements.on.argsForCall[0][0]).toBe('change');
//        expect(typeof mockElements.on.argsForCall[0][1]).toBe('function');
//    });
//
//});