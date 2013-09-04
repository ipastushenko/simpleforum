function doAjaxPost() {
    var name = $('#name').val();
    var education = $('#education').val();
    $.ajax({
    type: "POST",
    url: "/AjaxWithSpringMVC2Annotations/AddUser.htm",
    data: "name=" + name + "&education=" + education,
    success: function(response){
        $('#info').html(response);
        $('#name').val('');
        $('#education').val('');
    },
    error: function(e){
        alert('Error: ' + e);
    }
    });
}
