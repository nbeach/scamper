import angularApp from '../AngularApp';

function FileReaderService(AppSettings) {

    var fileQueue = [];

    function fileReadComplete(filename, mimeType, event, callback) {
        var data = event.target.result;

        //If the file is an image
        if(AppSettings.file.image.mimeTypes.indexOf(mimeType) >= 0) {
            //Strip off the data URL so it's just base64 encoded binary
            data = data.replace('data:' + mimeType + ';base64,', '');
        }

        //Create the file object
        var file = {
            name: filename,
            mimeType: mimeType,
            data: data
        };

        //Give the file to the callback
        callback(file);
    }

    function readFile(file, callback) {
        var reader = new window.FileReader();

        //Set the callback for when the file is loaded
        reader.onload = function(event) {
            fileReadComplete(file.name, file.type, event, callback);
        };

        //If the file is text read it as text
        if(AppSettings.file.csv.mimeTypes.indexOf(file.type) >= 0) {
            reader.readAsText(file);
        } else {
            //Read the file as a data URL so it's in Base64
            reader.readAsDataURL(file);
        }
    }

    function processQueue(callback) {
        if(fileQueue.length > 0) {
            var queuedFile = fileQueue.shift();

            readFile(queuedFile, function(readFile){
                processQueue(callback);
                callback(readFile);
            });
        }
    }

    this.readFilesFromEvent = function(event, callback) {

        var files = (event.dataTransfer || event.target).files;

        //For each file
        for(var i = 0; i < files.length; i++) {
            fileQueue.push(files[i]);
        }

        processQueue(callback);
    };

}

angularApp.service('FileReaderService', FileReaderService);
export default FileReaderService;