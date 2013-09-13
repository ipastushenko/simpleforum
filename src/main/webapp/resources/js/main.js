var orderTitles = 0; //global
var currentDate = 0; //global
var currentCountElements = 7;
var url = '/SimpleForum/';

function updateTopics(url, currentCountElements, orderTitles) {
    currentDate = 0;
    $('#topicBody').html('');
    appendTopics(url, currentCountElements, orderTitles);
}

function appendTopics(url, currentCountElements, orderTitles) {
    var urlGet =  url + 'json/titles/' + currentDate + '/' + currentCountElements + '/' + orderTitles;
    $.getJSON(urlGet, {}, function(data) {
        if (data.success) {
            var tbody = $('#topicBody');
            appendListTopics(url, data, tbody);
            currentDate = parseInt(data.endDate);
        }
        else {
            alert('incorrect get');
        }
    });
}

function updateMessages(url, titleId, currentCountElements) {
    currentDate = 0;
    $('#messageBody').html('');
    appendMessages(url, titleId, currentCountElements);
}

function appendMessages(url, titleId, currentCountElements) {
    var urlGet = url + 'json/messages/' + titleId + '/' + currentDate + '/' + currentCountElements;
    $.getJSON(urlGet, {}, function(data) {
        if (data.success) {
            var tbody = $('#messageBody');
            appendListMessages(url, data, tbody);
            currentDate = parseInt(data.endDate);
        }
        else {
            alert('incorrect get');
        }
    });
}

function toMessages(url, name, id) {
    showObject($('#sendMessageBox'));
    $('#topicName').html(name);
    $('#titleIdMessageForm').val(id);
    swap($('#tableTopicHead'), $('#tableMessagesHead'));
    swap($('#tableTopicBody'), $('#tableMessageBody'));
    swap($('#buttonCreateTopic'), $('#labelTopicName'));
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

function clickCreateNewMessage(url) {
    currentDate = 0;
    var textSerializable = {'titleId': $('#titleIdMessageForm').val(), 'textMessage' : $('#textMessage').val()};
    $.postJSON(url+'json/messages', textSerializable, function(data) {
        if (data.success){
            var titleId = parseInt($('#titleIdMessageForm').val());
            updateMessages(url, titleId, currentCountElements);
            $('#textMessage').val('');
        }
        else {
            var error = "Error: " + data.errors[0];
            alert(error);
        }
    });
}

function clickCreateNewTopic(url) {
    var nameSerializable = $('#textTopicName').serializeObject();
    $.postJSON(url+'json/titles', nameSerializable, function(data) {
        if (data.success){
            updateTopics(url, currentCountElements, orderTitles);
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
