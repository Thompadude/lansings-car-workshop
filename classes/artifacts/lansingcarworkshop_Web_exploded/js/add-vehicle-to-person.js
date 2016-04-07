$(document).ready(function () {

    var $registrationPlate, $make, $modelYear, $fuelType, $customerId;

    function isAllAddVehicleFormFieldsFilled() {
        var isFormValid = true;
        $('input').each(function () {
            if ($(this).val() === '' || $(this).val() === null) {
                $(this).fadeOut('fast').fadeIn('fast');
                $('#feedback').html('Fill in all form fields.').fadeIn('fast');
                isFormValid = false;
            }
        });
        return isFormValid;
    }

    function getUserInput() {
        $registrationPlate = $('input:text[name=vehicle-registration-plate]').val();
        $make = $('input:text[name=vehicle-make]').val();
        $modelYear = $('#date').val();
        $fuelType = $('input:text[name=vehicle-fuel-type]').val();
        $customerId = $('input:hidden[name=customer-id]').val();
    }

    function sendUserInputToCreateVehicleServlet() {
        if (isAllAddVehicleFormFieldsFilled()) {

            getUserInput();

            $.ajax({
                type: 'post',
                url: '/CreateVehicleServlet',
                data: {
                    'vehicle-registration-plate': $registrationPlate,
                    'vehicle-make': $make,
                    'vehicle-model-year': $modelYear,
                    'vehicle-fuel-type': $fuelType,
                    'customer-id': $customerId
                },
                success: function () {
                    $('#feedback').html($registrationPlate + " created.").hide().fadeIn('fast');
                },
                error: function () {
                    $('#feedback').html("Server communication error - contact webmaster!").hide().fadeIn('fast');
                }
            });
        }
    }

    $(document).on('click', '.btn.btn-success', function () {
        sendUserInputToCreateVehicleServlet();
    })

});