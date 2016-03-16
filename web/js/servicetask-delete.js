$(document).ready(function () {

    var $serviceTaskProfileWindow = '/servicetask/servicetask-profile.jsp';

    $(document).on('click', '.glyphicon.glyphicon-remove', function (event) {
        event.preventDefault();

        var $serviceTaskId = $(this).find('input[type=hidden]').val();

        $.ajax({
            type: 'get',
            url: '/DeleteServiceTaskServlet',
            data: {
                'deleteServiceTask': 'true',
                'serviceTaskId': $serviceTaskId
            },
            success: function (response) {
                if (window.location.pathname === $serviceTaskProfileWindow) {
                    $('.container').fadeOut('slow');
                } else {
                    $('#entry-' + $serviceTaskId).fadeOut('fast');
                }
            }
        })
    });

});