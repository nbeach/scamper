describe('dateRangePickerSpec', function() {

    var mockScope;
    var mockElement;

    var mockBeginDatePickerElement;
    var mockEndDatePickerElement;
    var mockBeginData;
    var mockEndData;
    var mockDatePicker;
    var mockConfigValues;


    var beginDatePickerElementSelector = 'input#begin-date-picker';
    var endDatePickerElementSelector = 'input#end-date-picker';

    beforeEach(function() {

        mockConfigValues = {
            initialBeginDate: moment().subtract(1, 'months'),
            initialEndDate: moment(),
            minimumDate: moment('2014-01-01', 'YYYY-MM-DD'),
            maximumDate: moment().endOf("day"),
            displayFormat: 'MM/DD/YYYY',
            initialView: 'months',
            dateChanged: function() {}
        };

        mockScope = {
            configuration: function(){
                return mockConfigValues;
            }
        };


        mockElement = {
            find: function() {}
        };

        mockBeginDatePickerElement = {
            datetimepicker: function() {},
            on: function() {},
            data: function() {},
            click: function() {}
        };

        mockEndDatePickerElement = {
            datetimepicker: function() {},
            on: function() {},
            data: function() {},
            click: function() {}
        };

        mockBeginData = {
            show: function() {},
            maxDate: function() {},
            date: function() {}
        };

        mockEndData = {
            show: function() {},
            minDate: function() {},
            date: function() {}
        };

        mockDatePicker = {
            click: function() {}
        };

        module('scamperApp');
    });

    function injectAndInitController() {
        var dateRangePickerCtrl;

        spyOn(mockElement, 'find').andCallFake(function(selector) {
            if(selector === beginDatePickerElementSelector) {
                return mockBeginDatePickerElement;
            } else if(selector === endDatePickerElementSelector) {
                return mockEndDatePickerElement;
            }
        });

        spyOn(mockBeginDatePickerElement, 'datetimepicker').andReturn(mockDatePicker);
        spyOn(mockEndDatePickerElement, 'datetimepicker').andReturn(mockDatePicker);

        spyOn(mockBeginDatePickerElement, 'data').andReturn(mockBeginData);
        spyOn(mockEndDatePickerElement, 'data').andReturn(mockEndData);

        inject(function ($controller) {
            dateRangePickerCtrl = $controller('dateRangePickerCtrl', {$scope: mockScope, $element: mockElement });
        });

        return dateRangePickerCtrl;
    }


    it('initializes the beginning datepicker', function () {
        injectAndInitController();

        expect(mockElement.find).toHaveBeenCalledWith(beginDatePickerElementSelector);
        expect(mockBeginDatePickerElement.datetimepicker).toHaveBeenCalledWith({
            format: 'MM/DD/YYYY',
            minDate: mockConfigValues.minimumDate,
            maxDate: mockConfigValues.initialEndDate,
            defaultDate: mockConfigValues.initialBeginDate,
            viewMode: mockConfigValues.initialView
        });
    });

    it('initializes the ending datepicker', function () {
        injectAndInitController();

        expect(mockElement.find).toHaveBeenCalledWith(endDatePickerElementSelector);
        expect(mockEndDatePickerElement.datetimepicker).toHaveBeenCalledWith({
            format: 'MM/DD/YYYY',
            minDate: mockConfigValues.initialBeginDate,
            maxDate: mockConfigValues.maximumDate,
            defaultDate: mockConfigValues.initialEndDate,
            viewMode: mockConfigValues.initialView
        });
    });

    it('shows the beginning datepicker when the field is clicked', function () {
        spyOn(mockBeginDatePickerElement, 'click');

        injectAndInitController();

        expect(mockBeginDatePickerElement.click).toHaveBeenCalledWith(mockBeginData.show);
    });

    it('shows the ending datepicker when the field is clicked', function () {
        spyOn(mockEndDatePickerElement, 'click');

        injectAndInitController();

        expect(mockEndDatePickerElement.click).toHaveBeenCalledWith(mockEndData.show);
    });

    it('constrains the end date when the begin date is changed', function () {
        spyOn(mockBeginDatePickerElement, 'on');
        spyOn(mockEndData, 'minDate');

        injectAndInitController();

        expect(mockBeginDatePickerElement.on).toHaveBeenCalled();
        var constrainFunction = mockBeginDatePickerElement.on.argsForCall[0][1];
        var mockEvent = {
            date: 'fakeDate'
        };
        constrainFunction(mockEvent);
        expect(mockEndData.minDate).toHaveBeenCalledWith(mockEvent.date);
    });

    it('constrains the begin date when the end date is changed', function () {
        spyOn(mockEndDatePickerElement, 'on');
        spyOn(mockBeginData, 'maxDate');

        injectAndInitController();

        expect(mockEndDatePickerElement.on).toHaveBeenCalled();
        var constrainFunction = mockEndDatePickerElement.on.argsForCall[0][1];
        var mockEvent = {
            date: 'fakeDate'
        };
        constrainFunction(mockEvent);
        expect(mockBeginData.maxDate).toHaveBeenCalledWith(mockEvent.date);
    });

    it('calls the callback function when the start date changes', function () {

        var mockEndDate = 'fakeEndDate';

        spyOn(mockBeginDatePickerElement, 'on');
        spyOn(mockConfigValues, 'dateChanged');
        spyOn(mockEndData, 'date').andReturn(mockEndDate);
        injectAndInitController();

        expect(mockBeginDatePickerElement.on).toHaveBeenCalled();
        var constrainFunction = mockBeginDatePickerElement.on.argsForCall[0][1];
        var mockEvent = {
            date: 'fakeDate'
        };
        constrainFunction(mockEvent);

        expect(mockConfigValues.dateChanged).toHaveBeenCalledWith(mockEvent.date, mockEndDate);
    });

    it('calls the callback function when the end date changes', function () {
        var mockBeginDate = 'fakeBeginDate';

        spyOn(mockEndDatePickerElement, 'on');
        spyOn(mockConfigValues, 'dateChanged');
        spyOn(mockBeginData, 'date').andReturn(mockBeginDate);
        injectAndInitController();

        expect(mockEndDatePickerElement.on).toHaveBeenCalled();
        var constrainFunction = mockEndDatePickerElement.on.argsForCall[0][1];
        var mockEvent = {
            date: 'fakeDate'
        };
        constrainFunction(mockEvent);

        expect(mockConfigValues.dateChanged).toHaveBeenCalledWith(mockBeginDate, mockEvent.date);
    });


});