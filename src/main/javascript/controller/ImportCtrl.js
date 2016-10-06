import angularApp from '../AngularApp';
import template from '../../html/import.html';

function ImportCtrl(TransactionService, ReceiptService, AppSettings) {

    var files = [];
    var SAVE_STATUS = {
        FAILED: 1,
        IN_PROGRESS: 2,
        COMPLETE: 3
    };

    this.saveStatus = function (){
        return SAVE_STATUS;
    };

    this.files = function() {
        return files;
    };

    this.isImage = function(file) {
        return AppSettings.file.image.mimeTypes.indexOf(file.mimeType) >= 0;
    };

    this.isCsv = function(file) {
        return AppSettings.file.csv.mimeTypes.indexOf(file.mimeType) >= 0;
    };

    this.fileLoaded = function(newFile) {
        var promise = null;

        if(AppSettings.file.csv.mimeTypes.indexOf(newFile.mimeType) >= 0) {
            promise = TransactionService.createFromCsv(newFile)

        } else {
            promise = ReceiptService.createFromImage(newFile);
        }

        promise.then( function(){ newFile.uploadStatus = SAVE_STATUS.COMPLETE }, function(){ newFile.uploadStatus = SAVE_STATUS.FAILED });
        newFile.uploadStatus = SAVE_STATUS.IN_PROGRESS;
        files.push(newFile);
    };
}

angularApp.controller('ImportCtrl', ImportCtrl);
export default ImportCtrl;