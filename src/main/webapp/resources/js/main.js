function lastAddedLiveFunc(url)
{
    $('div#lastPostsLoader').html('last post');

    $.getJSON(url + '/1', {}, function(data){
        if (data != "") {
            //console.log('add data..');
            $(".items").append('<li>ASdASd</li>');
        }
        $('div#lastPostsLoader').empty();
    });
};

function addAjax(url) {
    $(document).ready(function() {

       /* $('#sendMessage').submit(function() {
            $("#send").dialog("open");
            /*var sendMessage = $(this).serializeObject();
            $.postJSON(url, sendMessage, function(data) {
                if (data.success) {
                    alert("Complete");
                }
                else {
                    alert("Error: " + data.errors[0]);
                }
            });*/
         /*   return false;
        });


        $("#send").dialog({
            autoOpen: false,
            height: 150,
            width: 300,
            modal: true,
            buttons: {
                "Create topic": function() {
                    var addTopic = $("#newSend").serializeObject();
                    $.postJSON(url, addTopic, function(data) {
                        if (data.success) {
                            $("#send").dialog("close");
                            alert("success");
                        }
                        else {
                            alert("Error :" + data.errors[0]);
                        }
                    });
                },
            },
            close: function() {
                $(this).dialog("close");
            }
        });      */
        $(window).scroll(function(){
            var wintop = $(window).scrollTop(), docheight = $(document).height(), winheight = $(window).height();
            var  scrolltrigger = 0.95;
            if  ((wintop/(docheight-winheight)) > scrolltrigger) {
             //console.log('scroll bottom');
             lastAddedLiveFunc(url);
            }
        });
    });
}

 /*$("#sendMessage").submit(function (){
    alert('ok');
    /*var sendMessageForm = $(this).serializeObject();
    $.postJSON("json/message", sendMessageForm, function(data) {
        qwdqwd;
    });*/

   /* return false;
    /*var titleId = $('#titleId').val();
    var message = $('#textMessage').val();
    $.ajax({
    type: "POST",
    url: "/json/messages",
    data: "/" + titleId + "/" + message,
    success: function(response){
        $('#info').html(response);
        $('#titleId').val('');
        $('#textMessage').val('');
    },
    error: function(e){
        alert('Error: ' + e);
    }
    });*/
/*});

/*$("#sendAllert").live('submit', function() {
    alert("FORM SUBMITTED");
});*/
