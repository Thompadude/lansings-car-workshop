$(document).ready(function () {

    var $name, $address, $phone, $birthday, $sex, $role;

    function isAllCreatePersonFormFieldsFilled() {
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
        $name = $('input:text[name=person-name]').val();
        $address = $('input:text[name=person-address]').val();
        $phone = $('#tel').val();
        $birthday = $('#date').val();
        $sex = $('input:radio[name=person-sex]:checked').val();
        $role = $('input:radio[name=person-role]:checked').val()
    }

    function sendUserInputToCreatePersonServlet() {
        if (isAllCreatePersonFormFieldsFilled()) {

            getUserInput();

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
                    $('#feedback').html($name + response).hide().fadeIn('fast');
                },
                error: function () {
                    $('#feedback').html("Server communication error - contact webmaster!").hide().fadeIn('fast');
                }
            });
        }
    }

    $(document).on('click', '.btn.btn-success', function () {
        sendUserInputToCreatePersonServlet();
    })

});