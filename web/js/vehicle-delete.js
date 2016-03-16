$(document).ready(function () {

    var $vehicleProfileWindow = '/vehicle/vehicle-profile.jsp';

    $(document).on('click', '.glyphicon.glyphicon-remove', function (event) {
        event.preventDefault();

        var $vehicleId = $(this).find('input[type=hidden]').val();

        $.ajax({
            type: 'get',
            url: '/DeleteVehicleServlet',
            data: {
                'deleteVehicle': 'true',
                'vehicleId': $vehicleId
            },
            success: function (response) {
                if (window.location.pathname === $vehicleProfileWindow) {
                    $('.container').fadeOut('slow');
                } else {
                    $('#entry-' + $vehicleId).fadeOut('fast');
                }
            }
        })
    });

});