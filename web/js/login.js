$(document).ready(function () {

    var $userName, $password;

    var validateForm = function () {
        isFormValid = true;
        $('input').each(function (array) {
            if ($(this).val() === '' || $(this).val() === null) {
                $(this).fadeOut('fast').fadeIn('fast');
                isFormValid = false;
            }
        });
        return isFormValid;
    };

    var initializeVariables = function () {
        $userName = $('#username').val();
        $password = $('#password').val();
    };

    var loginPrompt = function () {
        initializeVariables();
        validateForm();

        if (isFormValid) {
            $.ajax({
                type: 'post',
                url: '/LoginServlet',
                data: {
                    'action': 'login',
                    'username': $userName,
                    'password': $password
                },
                success: function (response) {
                    document.location.replace("index.jsp");
                }
            });
        }
    };

    $(document).on('click', 'button[type=button]', function (event) {
        loginPrompt();
    });

    $(document).on('keypress', function (event) {
        if (event.which === 13) {
            loginPrompt();
        }
    });

});