describe('DataUrlFilter', function() {
    var filter;

    beforeEach(module('scamperApp'));
    beforeEach(inject(function($filter) {
        filter = $filter;
    }));

    it('adds data url formatting to base64 data', function () {
        var mockBase64 = 'mockbase64binary';
        var mockMimeType = 'image/png';

        var result =  filter('DataUrlFilter')(mockBase64, mockMimeType);
        expect(result).toBe('data:' + mockMimeType + ';base64,' + mockBase64);
    });

});