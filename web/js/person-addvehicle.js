$(document).ready(function () {

    var $registrationPlate, $make, $modelYear, $fuelType, $customerId, isFormValid;

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
        $registrationPlate = $('input:text[name=vehicle-registrationplate]').val();
        $make = $('input:text[name=vehicle-make]').val();
        $modelYear = $('#date').val();
        $fuelType = $('input:text[name=vehicle-fueltype]').val();
        $customerId = $('input:hidden[name=customerid]').val();
    };

    var createVehicle = function () {
        initializeVariables();
        validateForm();

        if (isFormValid) {
            $.ajax({
                type: 'post',
                url: '/CreateVehicleServlet',
                data: {
                    'vehicle-registrationplate': $registrationPlate,
                    'vehicle-make': $make,
                    'vehicle-modelyear': $modelYear,
                    'vehicle-fueltype': $fuelType,
                    'customerId': $customerId
                },
                success: function (response) {
                    $('#feedback').html(response).hide().fadeIn('fast');
                }
            });
        }
    };

    $('.btn.btn-success').click(function () {
            createVehicle();
        }
    );

});