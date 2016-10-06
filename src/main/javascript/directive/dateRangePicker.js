import angularApp from '../AngularApp';
import template from '../../html/date-range-picker.html';

function dateRangePickerCtrl($scope, $element) {

    var config = $scope.configuration();

    var beginDatePicker = $element.find('input#begin-date-picker');
    var endDatePicker = $element.find('input#end-date-picker');

    function initDatePicker(element, date, minDate, maxDate) {
        element.datetimepicker({
            format: config.displayFormat,
            minDate: minDate,
            maxDate: maxDate,
            defaultDate: date,
            viewMode: config.initialView
        });
    }

    function endDateChanged(event) {
        var datePickerData =  beginDatePicker.data("DateTimePicker");

        datePickerData.maxDate(event.date);
        config.dateChanged(datePickerData.date(), event.date);
    }

    function beginDateChanged(event) {
        var datePickerData =  endDatePicker.data("DateTimePicker");

        datePickerData.minDate(event.date);
        config.dateChanged(event.date, datePickerData.date());
    }

    initDatePicker(beginDatePicker, config.initialBeginDate, config.minimumDate, config.initialEndDate);
    beginDatePicker.on("dp.change", beginDateChanged);
    beginDatePicker.click(beginDatePicker.data("DateTimePicker").show);

    initDatePicker(endDatePicker, config.initialEndDate, config.initialBeginDate, config.maximumDate);
    endDatePicker.on("dp.change", endDateChanged);
    endDatePicker.click(endDatePicker.data("DateTimePicker").show);

}

angularApp
    .controller('dateRangePickerCtrl', dateRangePickerCtrl)
    .directive('dateRangePicker', function() {
        return {
            restrict: 'EA',
            template: template,
            scope: {
                configuration: '&config'
            },
            controllerAs: 'ctrl',
            controller: 'dateRangePickerCtrl'
        };
    });
export default dateRangePickerCtrl;