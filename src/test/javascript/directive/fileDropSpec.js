//import controller from '../../../main/javascript/directive/fileDrop';
//
//describe('fileDropSpec', function() {
//
//    var mockElements;
//    var mockDropAreaElement;
//    var mockFileReaderService;
//    var mockScope;
//
//    beforeEach(function() {
//
//        mockElements = {
//            on: function() {},
//            get: function() {}
//        };
//
//        mockDropAreaElement = {
//            addEventListener: function() {}
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
//    });
//
//    function injectAndInitController() {
//        var fileDropCtrl;
//
//        spyOn(mockElements, 'get').andReturn(mockDropAreaElement);
//
//        inject(function ($controller) {
//            fileDropCtrl = $controller('fileDropCtrl', {$scope: mockScope, $element: mockElements, FileReaderService: mockFileReaderService});
//        });
//
//        return fileDropCtrl;
//    }
//
//    function genericFileDropEventHandlerSetupTest(event, seqeunce) {
//        spyOn(mockElements, 'on');
//
//        injectAndInitController();
//
//        expect(mockElements.on.argsForCall[seqeunce][0]).toBe(event);
//        expect(typeof mockElements.on.argsForCall[seqeunce][1]).toBe('function');
//    }
//
//    it('sets an event handler to stop default/propagation on drag over', function () {
//        genericFileDropEventHandlerSetupTest('dragover', 0);
//    });
//
//
//    it('sets an event handler to stop default/propagation on drag leave', function () {
//        genericFileDropEventHandlerSetupTest('dragleave', 1);
//    });
//
//    it('sets an event handler for dropped files', function () {
//        spyOn(mockDropAreaElement, 'addEventListener');
//
//        injectAndInitController();
//
//        expect(mockElements.get.argsForCall[0][0]).toBe(0);
//        expect(mockDropAreaElement.addEventListener.argsForCall[0][0]).toBe('drop');
//        expect(typeof mockDropAreaElement.addEventListener.argsForCall[0][1]).toBe('function');
//    });
//
//    it('sets an event handler to stop default/propagation on drag leave', function () {
//        var mockEvent = {
//            stopPropagation: function() {},
//            preventDefault: function() {}
//        };
//
//        spyOn(mockElements, 'on');
//        spyOn(mockEvent, 'stopPropagation');
//        spyOn(mockEvent, 'preventDefault');
//
//        injectAndInitController();
//
//        var stopFunction = mockElements.on.argsForCall[1][1];
//        stopFunction(mockEvent);
//
//        expect(mockEvent.stopPropagation).toHaveBeenCalled();
//        expect(mockEvent.preventDefault).toHaveBeenCalled();
//    });
//
//    it('delegates file reading events to the file reader', function () {
//        var mockEvent = {
//            stopPropagation: function() {},
//            preventDefault: function() {}
//        };
//
//        spyOn(mockDropAreaElement, 'addEventListener');
//        spyOn(mockFileReaderService, 'readFilesFromEvent');
//
//        injectAndInitController();
//
//        var dropFunction = mockDropAreaElement.addEventListener.argsForCall[0][1];
//        dropFunction(mockEvent);
//
//        expect(mockFileReaderService.readFilesFromEvent).toHaveBeenCalledWith(mockEvent, mockScope.onLoad);
//
//    });
//});