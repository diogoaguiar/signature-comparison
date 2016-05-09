$(function () {
    //Load image lists
    $.ajax({
        type: 'GET',
        url: '',
        success: function(data) {
            console.log('success', data);
        }
    });

    //test
    $('#compare_button').on('click', function () {
        $('#title_row').slideToggle(200);
    });
});

function displayImage(fieldId, byteArray) {
    document.getElementById(fieldId).src = "data:image/png;base64," + byteArray;
}