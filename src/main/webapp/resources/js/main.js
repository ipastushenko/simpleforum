function appendTopic(tbody, createtime, changetime, name, id) {
    tbody.append('<tr>');
    tbody.append('<td class="span3"><p class="text-center">'+ createtime + '</p></td>');
    tbody.append('<td class="span3"><p class="text-center">'+ changetime + '</p></td>');
    //TODO:url value;
    tbody.append('<td class="span5"><a href=\'<c:url value="/"/>\'>'+ name + '</a></td>');
    tbody.append('<td class="span1"><p class="text-center"><a href=\'<c:url value="/"/>\'>'+ id +'</a></p></td>');
    tbody.append('</tr>');
}

function clickCreateNewTopic(url) {
    var nameSerialize = form.serializeObject();
    $.postJSON(url+'json/titles', nameSerialize, function(data) {
        if (data.success){
            //TODO: Add to database and update topicTable
            $('#modalCreateTopic').modal('hide');
            $('#inputNameTopic').val('');
            $('#errorCreateTopic').slideUp(0);
        }
        else {
            var error = "Error: " + data.errors[0];
            $('#errorCreateTopic').html(error);
            $('#errorCreateTopic').fadeIn(0);
        }
    });
}

function addAjax(url) {
    $(document).ready(function() {
        $('#btnCreateNewTopic').click(function() {
            clickCreateNewTopic(url);
        });

        $('#closeCreateTopic').click(function() {
            $('#errorCreateTopic').slideUp(0);
        });

//TODO:temlate adding topics
        $.getJSON(url + 'json/titles/1', {}, function(data) {
            var tbody = $('#topicBody');
            tbody.html('');
            if (data.success) {
                for (var i in data.elements) {
                    appendTopic(tbody, 'UTS', 'UTS', data.elements[i], data.elementIds[i]);
                }
            }
            else {
                alert('incorrect get');
            }
        });
        $.getJSON(url + 'json/titles/2', {}, function(data) {
            var tbody = $('#topicBody');
            if (data.success) {
                for (var i in data.elements) {
                    appendTopic(tbody, 'UTS', 'UTS', data.elements[i], data.elementIds[i]);
                }
            }
            else {
                alert('incorrect get');
            }
        });
    });
}
