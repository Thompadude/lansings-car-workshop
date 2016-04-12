$(document).ready(function () {

    var urlToPersonProfile = '/person/person-profile.jsp';

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
            success: function () {
                if (window.location.pathname === urlToPersonProfile) {
                    $('.container').fadeOut('fast');
                } else {
                    $('#entry-' + $personId).fadeOut('fast');
                }
            },
            error: function () {
                alert("Person already deleted!");
            }
        })
    });

});