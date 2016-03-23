$(document).ready(function () {

    $(document).on('click', '#togglecompletion', function (event) {
        event.preventDefault();

        var $serviceTaskId = $(document).find('input[type=hidden]').val();

        $.ajax({
            type: 'post',
            url: '/UpdateServiceTaskServlet',
            data: {
                'action': 'toggle-completion',
                'service-task-id': $serviceTaskId
            },
            success: function () {
                $('.iscompleted').load(location.href + " .iscompleted");
            },
            error: function () {
                alert("Server communication error - contact webmaster!");
            }
        })
    });

});