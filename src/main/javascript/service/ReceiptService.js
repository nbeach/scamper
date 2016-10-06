import angularApp from '../AngularApp';
import AbstractService from './AbstractService';

class ReceiptService extends AbstractService {

    constructor(ReceiptDao) {
        super(ReceiptDao);
    }

    createFromImage(file) {
        var receipt = {
            mimeType: file.mimeType,
            file: file.data
        };
        return this.create(receipt);
    }

}

angularApp.service('ReceiptService', ReceiptService);
export default ReceiptService;