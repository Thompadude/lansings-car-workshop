$(document).ready(function () {

    var urlToVehicleProfile = '/vehicle/vehicle-profile.jsp';

    $(document).on('click', '.glyphicon.glyphicon-remove', function (event) {
        event.preventDefault();

        var $vehicleId = $(this).find('input[type=hidden]').val();

        $.ajax({
            type: 'get',
            url: '/DeleteVehicleServlet',
            data: {
                'deleteVehicle': 'true',
                'vehicle-id': $vehicleId
            },
            success: function () {
                if (window.location.pathname === urlToVehicleProfile) {
                    $('.container').fadeOut('slow');
                } else {
                    $('#entry-' + $vehicleId).fadeOut('fast');
                }
            },
            error: function () {
                alert("Server communication error - contact webmaster!");
            }
        })
    });

});