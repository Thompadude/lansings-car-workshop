$(document).ready(function () {

    var $personProfileWindow = '/person/person-profile.jsp';

    $(document).on('click', '.glyphicon.glyphicon-remove', function (event) {
        event.preventDefault();

        var $personId = $(this).find('input[type=hidden]').val();

        $.ajax({
            type: 'get',
            url: '/DeletePersonServlet',
            data: {
                'deletePerson': 'true',
                'personId': $personId
            },
            success: function (response) {
                if (window.location.pathname === $personProfileWindow) {
                    $('.container').fadeOut('fast');
                } else {
                    $('#entry-' + $personId).fadeOut('fast');
                }
            }
        })
    });

});