var orderTitles = 0;
var currentDate = 0;
var currentCountElements = 7;
var url = prefixUrl();

function updateTopics(url, tbody, currentCountElements, orderTitles) {
    currentDate = 0;
    tbody.html('');
    appendTopics(url, tbody, currentCountElements, orderTitles);
}

function appendTopics(url, tbody, currentCountElements, orderTitles) {
    var urlGet =  url + 'json/titles/' + currentDate + '/' + currentCountElements + '/' + orderTitles;
    $.getJSON(urlGet, {}, function(data) {
        if (data.success) {
            appendListTopics(url, data, tbody);
            currentDate = parseInt(data.endDate);
        }
        else {
            alert('incorrect get');
        }
    });
}

function updateMessages(url, tbody, titleId, currentCountElements) {
    currentDate = 0;
    tbody.html('');
    appendMessages(url, tbody, titleId, currentCountElements);
}

function appendMessages(url, tbody, titleId, currentCountElements) {
    var urlGet = url + 'json/messages/' + titleId + '/' + currentDate + '/' + currentCountElements;
    $.getJSON(urlGet, {}, function(data) {
        if (data.success) {
            appendListMessages(url, data, tbody);
            currentDate = parseInt(data.endDate);
        }
        else {
            alert('incorrect get');
        }
    });
}

function toMessages(url, name, id) {
    controller.navigate("messages/" + id, true);
    updateMessages(url, id, currentCountElements);
}

function deleteTopic(url, id) {
    $.getJSON(url + 'json/removeTitle/' + id, {}, function(data) {
        if (data.success) {
            updateTopics(url, currentCountElements, orderTitles);
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
            updateMessages(url, titleId, currentCountElements);
        }
        else {
            alert('incorrect get');
        }
    });
}

function clickCreateNewMessage(url, form) {
    currentDate = 0;
    var textSerializable = form.serializeObject();
    $.postJSON(url+'json/messages', textSerializable, function(data) {
        if (data.success){
            var titleId = parseInt(appState.get("titleId"));
            updateMessages(url, titleId, currentCountElements);
            //$('#textMessage').val('');
        }
        else {
            var error = "Error: " + data.errors[0];
            alert(error);
        }
    });
}

function clickCreateNewTopic(url, form) {
    currentDate = 0;
        var textSerializable = form.serializeObject();
        $.postJSON(url+'json/titles', textSerializable, function(data) {
            if (data.success){
                var titleId = parseInt(appState.get("titleId"));
                updateTopics(url, titleId, currentCountElements);
                //$('#textMessage').val('');
            }
            else {
                var error = "Error: " + data.errors[0];
                alert(error);
            }
        });
}

function scrollTopic(url, objectScroll) {
    if ((objectScroll.scrollTop + objectScroll.clientHeight) / objectScroll.scrollHeight > 0.9) {
        appendTopics(url, currentCountElements, orderTitles);
    }
}

function scrollMessage(url, objectScroll) {
    if ((objectScroll.scrollTop + objectScroll.clientHeight) / objectScroll.scrollHeight > 0.9) {
        var titleId = parseInt($('#titleIdMessageForm').val());
        appendMessages(url, titleId ,currentCountElements);
    }
}

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
        orderTitles = 0;
        updateTopics(url, currentCountElements, orderTitles);
    });

    $('#lastUpdateDateSort').click(function() {
        orderTitles = 1;
        updateTopics(url, currentCountElements, orderTitles);
    });

    $('#lastUpdateTopic').click(function() {
        $('#sendMessageBox').css('visibility', 'hidden');
        swap($('#tableMessagesHead'), $('#tableTopicHead'));
        swap($('#tableMessageBody'), $('#tableTopicBody'));
        swap($('#labelTopicName'), $('#buttonCreateTopic'));
        orderTitles = 1;
        updateTopics(url, currentCountElements, orderTitles);
    });

    updateTopics(url, currentCountElements, orderTitles);
});
