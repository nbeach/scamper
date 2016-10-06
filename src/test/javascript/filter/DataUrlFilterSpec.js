import DataUrlFilter from '../../../main/javascript/filter/DataUrlFilter';

describe('DataUrlFilter', function() {
    var filter;

    beforeEach(function() {
        filter = DataUrlFilter();
    });

    it('adds data url formatting to base64 data', function () {
        var mockBase64 = 'mockbase64binary';
        var mockMimeType = 'image/png';

        var result =  filter(mockBase64, mockMimeType);
        expect(result).toBe('data:' + mockMimeType + ';base64,' + mockBase64);
    });

});