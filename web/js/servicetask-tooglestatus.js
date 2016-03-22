$(document).ready(function () {

    $(document).on('click', '#togglecompletion', function (event) {
        event.preventDefault();

        var $servicetaskid = $(document).find('input[type=hidden]').val();

        $.ajax({
            type: 'post',
            url: '/UpdateServiceTaskServlet',
            data: {
                'action': 'togglecompletion',
                'servicetaskid': $servicetaskid
            },
            success: function (response) {
                $('.iscompleted').load(location.href + " .iscompleted");
            }
        })
    });

});