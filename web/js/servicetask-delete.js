$(document).ready(function () {

    var urlToServiceTaskProfile = '/servicetask/servicetask-profile.jsp';

    $(document).on('click', '.glyphicon.glyphicon-remove', function (event) {
        event.preventDefault();

        var $serviceTaskId = $(this).find('input[type=hidden]').val();

        $.ajax({
            type: 'get',
            url: '/DeleteServiceTaskServlet',
            data: {
                'deleteServiceTask': 'true',
                'service-task-id': $serviceTaskId
            },
            success: function () {
                if (window.location.pathname === urlToServiceTaskProfile) {
                    $('.container').fadeOut('slow');
                } else {
                    $('#entry-' + $serviceTaskId).fadeOut('fast');
                }
            },
            error: function () {
                alert("Server communication error - contact webmaster!");
            }
        })
    });

});