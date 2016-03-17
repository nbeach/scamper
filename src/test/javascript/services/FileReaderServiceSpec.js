describe('FileReaderServiceSpec', function() {

    var fileReaderService;

    beforeEach( function() {
        module('scamperApp');

        inject(function (FileReaderService) {
            fileReaderService = FileReaderService;
        });
    });


    function initMockFileReader(mockResultEvent, readAsTextSpy, readAsDataUrlSpy) {

        FileReader = function() {
            var self = this;
            this.onload = null;

            this.readAsText = function () {
                readAsTextSpy();
                this.onload(mockResultEvent);
            };

            this.readAsDataURL = function () {
                readAsDataUrlSpy();
                this.onload(mockResultEvent);
            };
        };

    }

    it('reads files from events as text', function() {

        var mockReadableEvent = {
            target: {
                files: [{
                        name: 'csv1.txt',
                        type: 'text/csv'
                    },{
                        name: 'csv2.txt',
                        type: 'text/csv'
                    }]
            }
        };

        var mockResultEvent = {
            target: {
                result: 'mock file text'
            }
        };

        var readAsTextSpy = jasmine.createSpy();
        initMockFileReader(mockResultEvent, readAsTextSpy, null);

        var returnedFiles = [];
        function callback(file) {
            returnedFiles.push(file);
        }

        fileReaderService.readFilesFromEvent(mockReadableEvent, callback);

        expect(returnedFiles[0].name).toBe(mockReadableEvent.target.files[1].name);
        expect(returnedFiles[0].mimeType).toBe(mockReadableEvent.target.files[1].type);
        expect(returnedFiles[0].data).toBe(mockResultEvent.target.result);

        expect(returnedFiles[1].name).toBe(mockReadableEvent.target.files[0].name);
        expect(returnedFiles[1].mimeType).toBe(mockReadableEvent.target.files[0].type);
        expect(returnedFiles[1].data).toBe(mockResultEvent.target.result);

        expect(readAsTextSpy.callCount).toBe(2);

    });


    it('reads files from events as images', function() {

        var mockReadableEvent = {
            target: {
                files: [{
                        name: 'image1.png',
                        type: 'image/png'
                    },{
                        name: 'image2.png',
                        type: 'image/png'
                    }]
            }
        };

        var mockResultEvent = {
            target: {
                result: 'data:image/png;base64,mockbase64binary'
            }
        };

        var readAsDataUrlSpy = jasmine.createSpy();
        initMockFileReader(mockResultEvent, null, readAsDataUrlSpy);

        var returnedFiles = [];
        function callback(file) {
            returnedFiles.push(file);
        }

        fileReaderService.readFilesFromEvent(mockReadableEvent, callback);

        expect(returnedFiles[0].name).toBe(mockReadableEvent.target.files[1].name);
        expect(returnedFiles[0].mimeType).toBe(mockReadableEvent.target.files[1].type);
        expect(returnedFiles[0].data).toBe('mockbase64binary');

        expect(returnedFiles[1].name).toBe(mockReadableEvent.target.files[0].name);
        expect(returnedFiles[1].mimeType).toBe(mockReadableEvent.target.files[0].type);
        expect(returnedFiles[1].data).toBe('mockbase64binary');

        expect(readAsDataUrlSpy.callCount).toBe(2);

    });


});