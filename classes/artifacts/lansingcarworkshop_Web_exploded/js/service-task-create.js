$(document).ready(function () {

    var $vehicleId, $employeeId, $serviceTime, $serviceNote;

    function setDateTimePlaceholder() {
        var date = new Date();
        var todaysDate = date.getFullYear() + '-01-01T' + date.getHours() + ':00:00.00';
        $('#service-time').val(todaysDate);
    }

    setDateTimePlaceholder();

    function isAllCreateServiceTaskFormFieldsFilled() {
        var isFormValid = true;
        $('input').each(function (array) {
            if ($(this).val() === '' || $(this).val() === null) {
                $(this).fadeOut('fast').fadeIn('fast');
                $('#feedback').html('Fill in all form fields.').fadeIn('fast');
                isFormValid = false;
            }
        });
        return isFormValid;
    }

    function getUserInput() {
        $vehicleId = $('input:hidden').val();
        $employeeId = $('#employee-list option:selected').val();
        $serviceTime = $('#service-time').val();
        $serviceNote = $('input:text[name=service-note]').val();
    }

    function sendUserInputToCreateServiceTaskServlet() {
        if (isAllCreateServiceTaskFormFieldsFilled()) {

            getUserInput();

            $.ajax({
                type: 'post',
                url: '/CreateServiceTaskServlet',
                data: {
                    'service-note': $serviceNote,
                    'service-time': $serviceTime,
                    'vehicle-id': $vehicleId,
                    'employee-id': $employeeId,
                },
                success: function (response) {
                    $('#feedback').html(response).hide().fadeIn('fast');
                },
                error: function () {
                    $('#feedback').html("Server communication error - contact webmaster!").hide().fadeIn('fast');
                }
            });
        }
    }

    $(document).on('click', '.btn.btn-success', function () {
        sendUserInputToCreateServiceTaskServlet();
    })

});