var orderTitles = 0;
var countBeginElements = 7;
var countLoadElements = 5;
var currentDate = 0;
var currentCountElements = countBeginElements;

function appendTopic(url, tbody, createtime, changetime, name, id) {
    tbody.append('<tr>');
    tbody.append('<td class="span3"><p class="text-center">'+ createtime + '</p></td>');
    tbody.append('<td class="span3"><p class="text-center">'+ changetime + '</p></td>');
    tbody.append('<td class="span5"><a href="#" onclick="toMessages(' + url + ',\'' + name + '\',' + id + ')">'+ name + '</a></td>');
    tbody.append('<td class="span1"><p class="text-center"><button class="btn" onclick="deleteTopic(' + url + ',' + id +')">'+ 'delete' +'</button></p></td>');
    tbody.append('</tr>');
}

function appendMessage(url, tbody, createtime, name, id) {
    tbody.append('<tr>');
    tbody.append('<td class="span3"><p class="text-center">'+ createtime + '</p></td>');
    tbody.append('<td class="span8"><p class="text-left">' + name + '</p></td>');
    tbody.append('<td class="span1"><p class="text-center"><button class="btn" onclick="deleteMessage(' + url + ',' + id +')">'+ 'delete' +'</button></p></td>');
    tbody.append('</tr>');
}

function updateListTopics(url, data, tbody) {
    tbody.html('');
    for (var i in data.elements) {
        appendTopic(url,
                tbody,
                new Date(data.elements[i].createDate),
                new Date(data.elements[i].lastUpdateDate),
                data.elements[i].name,
                data.elements[i].id
        );
    }
}

function updateListMessages(url, data, tbody) {
     tbody.html('');
     for (var i in data.elements) {
         appendMessage(url,
                 tbody,
                 new Date(data.elements[i].createDate),
                 data.elements[i].textMessage,
                 data.elements[i].id
         );
     }
}

function updateTopics(url, currentDate, currentCountElements, orderTitles) {
    var urlGet = url + 'json/titles/' + currentDate + '/' + currentCountElements + '/' + orderTitles;
    $.getJSON(urlGet, {}, function(data) {
        if (data.success) {
            var tbody = $('#topicBody');
            updateListTopics(url, data, tbody);
            currentDate = data.endDate;
        }
        else {
            alert('incorrect get');
        }
    });
}

function updateMessages(url, titleId, currentDate, currentCountElements) {
    var urlGet = url + 'json/messages/' + titleId + '/' + currentDate + '/' + currentCountElements;
    $.getJSON(urlGet, {}, function(data) {
        if (data.success) {
            var tbody = $('#messageBody');
            updateListMessages(url, data, tbody);
            currentDate = data.endDate;
        }
        else {
            alert('incorrect get');
        }
    });
}

function hiddenObject(object) {
    object.css('visibility', 'hidden');
    object.css('position', 'absolute');
}

function showObject(object) {
    object.css('position', 'relative');
    object.css('visibility', 'visible');
}

function swap(objectToHidden, objectToVisible) {
    hiddenObject(objectToHidden);
    showObject(objectToVisible);

}

function toMessages(url, name, id) {
    currentDate = 0;
    showObject($('#sendMessageBox'));
    $('#topicName').html(name);
    $('#titleIdMessageForm').val(id);
    swap($('#tableTopicHead'), $('#tableMessagesHead'));
    swap($('#tableTopicBody'), $('#tableMessageBody'));
    swap($('#buttonCreateTopic'), $('#labelTopicName'));
    updateMessages(url, id, currentDate, currentCountElements);
}

function deleteTopic(url, id) {
    $.getJSON(url + 'json/removeTitle/' + id, {}, function(data) {
        if (data.success) {
            updateTopics(url, currentDate, currentCountElements, orderTitles);
        }
        else {
            alert('incorrect get');
        }
    });
}

function deleteMessage(url, id) {
    $.getJSON(url + 'json/removeMessage/' + id, {}, function(data) {
        if (data.success) {
            var titleId = parseInt($('#titleIdMessageForm').val());
            updateMessages(url, titleId, currentDate, currentCountElements);
        }
        else {
            alert('incorrect get');
        }
    });
}

function clickCreateNewMessage(url) {
    currentDate = 0;
    var textSerializable = {'titleId': $('#titleIdMessageForm').val(), 'textMessage' : $('#textMessage').val()};
    $.postJSON(url+'json/messages', textSerializable, function(data) {
        if (data.success){
            var titleId = parseInt($('#titleIdMessageForm').val());
            updateMessages(url, titleId, currentDate, currentCountElements);
            $('#textMessage').val('');
        }
        else {
            var error = "Error: " + data.errors[0];
            alert(error);
        }
    });
}

function clickCreateNewTopic(url) {
    currentDate = 0;
    var nameSerializable = $('#textTopicName').serializeObject();
    $.postJSON(url+'json/titles', nameSerializable, function(data) {
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

        $('#btnSendMessage').click(function() {
            clickCreateNewMessage(url);
        });

        $('#creationDateSort').click(function() {
            currentDate = 0;
            orderTitles = 0;
            updateTopics(url, currentDate, currentCountElements, orderTitles);
        });

        $('#lastUpdateDateSort').click(function() {
            currentDate = 0;
            orderTitles = 1;
            updateTopics(url, currentDate, currentCountElements, orderTitles);
        });

        $('#lastUpdateTopic').click(function() {
            currentDate = 0;
            $('#sendMessageBox').css('visibility', 'hidden');
            swap($('#tableMessagesHead'), $('#tableTopicHead'));
            swap($('#tableMessageBody'), $('#tableTopicBody'));
            swap($('#labelTopicName'), $('#buttonCreateTopic'));
            orderTitles = 1;
            updateTopics(url, currentDate, currentCountElements, orderTitles);
        });



        updateTopics(url, currentDate, currentCountElements, orderTitles);
    });
}
