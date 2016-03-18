$(document).ready(function () {

    var $name, $address, $phone, $birthday, $sex, $role, isFormValid;

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
        $name = $('input:text[name=person-name]').val();
        $address = $('input:text[name=person-address]').val();
        $phone = $('#tel').val();
        $birthday = $('#date').val();
        $sex = $('input:radio[name=person-sex]:checked').val();
        $role = $('input:radio[name=person-role]:checked').val()
    };

    var createPerson = function () {
        initializeVariables();
        validateForm();

        if (isFormValid) {
            $.ajax({
                type: 'post',
                url: '/CreatePersonServlet',
                data: {
                    'person-name': $name,
                    'person-address': $address,
                    'person-phone': $phone,
                    'person-birthday': $birthday,
                    'person-sex': $sex,
                    'person-role': $role
                },
                success: function (response) {
                    console.log("SUCCESS!");
                    $('#feedback').html($name + response).hide().fadeIn('fast');
                }
            });
        }
    };

    $('.btn.btn-success').click(function () {
            createPerson();
        }
    );

});