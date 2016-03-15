$(document).ready(function () {

    var $vehicleId = $('#vehicleId').val();

    $('.glyphicon.glyphicon-remove').click(function () {
        $.ajax({
            type: 'get',
            url: '/DeleteVehicleServlet',
            data: {
                'deleteVehicle': 'true',
                'vehicleId': $vehicleId
            },
            success: function () {
                $('.container').fadeOut('slow');
            }
        })
    });

});