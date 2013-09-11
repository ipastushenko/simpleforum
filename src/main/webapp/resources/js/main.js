var countBeginElements = 7;
var countLoadElements = 5;
var currentDate = 0;
var currentCountElements = countBeginElements;
var orderTitles = 0;

function appendTopic(url, tbody, createtime, changetime, name, id) {
    tbody.append('<tr>');
    tbody.append('<td class="span3"><p class="text-center">'+ createtime + '</p></td>');
    tbody.append('<td class="span3"><p class="text-center">'+ changetime + '</p></td>');
    //TODO:url value;
    tbody.append('<td class="span5"><a href=\'<c:url value="/"/>\'>'+ name + '</a></td>');
    tbody.append('<td class="span1"><p class="text-center"><button class="btn" onclick="deleteTopic(' + url + ',' + id +')">'+ 'delete' +'</a></p></td>');
    tbody.append('</tr>');
}

function updateListTopics(url, data, tbody) {
    tbody.html('');
    for (var i in data.elements) {
        if (i != data.elements.length - 1) {
            appendTopic(url,
                    tbody,
                    new Date(data.elements[i].createDate),
                    new Date(data.elements[i].lastUpdateDate),
                    data.elements[i].name,
                    data.elements[i].id
            );
        }
    }
}

function updateTopics(url, currentDate, currentCountElements, orderTitles) {
    $.getJSON(url + 'json/titles/' + currentDate + '/' + currentCountElements + '/' + orderTitles, {}, function(data) {
    var tbody = $('#topicBody');
        if (data.success) {
            updateListTopics(url, data, tbody);
            currentDate = data.endDate;
        }
        else {
            alert('incorrect get');
        }
    });
}

function deleteTopic(url, id) {
    $.getJSON(url + 'json/removeTitle/' + id, {}, function(data) {
        var tbody = $('#topicBody');
        if (data.success) {
            updateTopics(url, currentDate, currentCountElements, orderTitles);
        }
        else {
            alert('incorrect get');
        }
    });
}

function clickCreateNewTopic(url) {
    var nameSerialize = $('#textTopicName').serializeObject();
    $.postJSON(url+'json/titles', nameSerialize, function(data) {
        if (data.success){
            updateTopics(url, currentDate, currentCountElements, orderTitles);
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

        updateTopics(url, currentDate, currentCountElements, orderTitles);
    });
}
