$(document).ready(function () {

    function isAllUpdateFormFieldsFilled() {
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

    $('#updateform').submit(function (event) {
        if (!isAllUpdateFormFieldsFilled()) {
            event.preventDefault();
        }
    })

});