$(document).ready(function () {

    var $vehicleId, $employeeId, $serviceTime, $serviceNote, isFormValid;

    setDateTimePlaceholder();

    var validateForm = function () {
        isFormValid = true;
        $('input').each(function (array) {
            if ($(this).val() === '' || $(this).val() === null) {
                $(this).fadeOut('fast').fadeIn('fast');
                $('#feedback').html('Fill in all form fields.').fadeIn('fast');
                isFormValid = false;
            }
        });
        return isFormValid;
    };

    var initializeVariables = function () {
        $vehicleId = $('input:hidden').val();
        $employeeId = $('#employee-list option:selected').val();
        $serviceTime = $('#service-time').val();
        $serviceNote = $('input:text[name=service-note]').val();
    };

    var createServiceTask = function () {
        initializeVariables();
        validateForm();

        if (isFormValid) {
            $.ajax({
                type: 'post',
                url: '/CreateServiceTaskServlet',
                data: {
                    'service-time': $serviceTime,
                    'service-note': $serviceNote,
                    'service-time': $serviceTime,
                    'vehicleid': $vehicleId,
                    'employeeid': $employeeId,
                },
                success: function (response) {
                    $('#feedback').html(response).hide().fadeIn('fast');
                }
            });
        }
    };

    $('.btn.btn-success').click(function () {
            createServiceTask();
        }
    );

});

function setDateTimePlaceholder() {
    var date = new Date();
    var todaysDate = date.getFullYear() + '-01-01T' + date.getHours() + ':00:00.00';
    $('#service-time').val(todaysDate);
};