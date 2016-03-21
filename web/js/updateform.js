$(document).ready(function () {

    var isFormValid;

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

    $('#updateform').submit(function(event){
        validateForm();

        console.log("clicked")

        if (!isFormValid) {
            event.preventDefault();
        }
    })

});
