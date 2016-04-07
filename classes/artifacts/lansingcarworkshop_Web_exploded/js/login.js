$(document).ready(function () {

    function isLoginFormFieldsFilled() {
        var isFormValid = true;
        $('input').each(function () {
            if ($(this).val() === '' || $(this).val() === null) {
                $(this).fadeOut('fast').fadeIn('fast');
                isFormValid = false;
            }
        });
        return isFormValid;
    }

    function getUserName() {
        return $('#username').val();
    }

    function getPassword() {
        return $('#password').val();
    }

    function sendUserInputToLoginServletForValidation() {
        if (isLoginFormFieldsFilled()) {
            $.ajax({
                type: 'post',
                url: '/LoginServlet',
                data: {
                    'action': 'login',
                    'username': getUserName(),
                    'password': getPassword()
                },
                success: function () {
                    document.location.replace("index.jsp");
                },
                error: function() {
                    alert("Server communication error - contact webmaster!")
                }
            });
        }

    }

    $(document).on('click', 'button[type=button]', function () {
        sendUserInputToLoginServletForValidation();
    });

    $(document).on('keypress', function (event) {
        if (event.which === 13) {
            sendUserInputToLoginServletForValidation();
        }
    })

});