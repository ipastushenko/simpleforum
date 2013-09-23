var orderTitles = 0;
var currentDate = 0;
var currentCountElements = 10;
var currentCountMessages = 0;
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
    currentCountMessages = 0;
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

function deleteTopic(url, id) {
    $.getJSON(url + 'json/removeTitle/' + id, {}, function(data) {
        if (data.success) {
            updateTopics(url, $('.js-table-body'), currentCountElements, orderTitles);
        }
        else {
            alert('incorrect get');
        }
    });
}

function deleteMessage(url, id) {
    $.getJSON(url + 'json/removeMessage/' + id, {}, function(data) {
        if (data.success) {
            updateMessages(url, $('.js-table-body'), parseInt(appState.get("titleId")), currentCountElements);
        }
        else {
            alert('incorrect get');
        }
    });
}

function clickCreateNewMessage(url, form) {
    currentDate = 0;
    var textSerializable = {'titleId': parseInt(appState.get("titleId")), 'textMessage' : $('.js-message-text').val()};
    $.postJSON(url+'json/messages', textSerializable, function(data) {
        if (data.success){
            $('.js-message-text').val('');
            updateMessages(url, $('.js-table-body'), parseInt(appState.get("titleId")), currentCountElements);
        }
        else {
            var error = "Error: " + data.errors[0];
            alert(error);
        }
    });
}

function clickCreateNewTopic(url, form) {
    currentDate = 0;
    var textSerializable = {'name' : $('.js-new-topic-name').val()};
    $.postJSON(url+'json/titles', textSerializable, function(data) {
        if (data.success){
            $('.js-new-topic-name').val('');
            updateTopics(url, $('.js-table-body'), currentCountElements, orderTitles);
        }
        else {
            var error = "Error: " + data.errors[0];
            alert(error);
        }
    });
}

function scrollTopic(url, objectScroll) {
    if ((objectScroll[0].scrollTop + objectScroll[0].clientHeight) / objectScroll[0].scrollHeight > 0.95) {
        appendTopics(url, $('.js-table-body'), currentCountElements, orderTitles);
    }
}

function scrollMessage(url, objectScroll) {
    if ((objectScroll[0].scrollTop + objectScroll[0].clientHeight) / objectScroll[0].scrollHeight > 0.95) {
        appendMessages(url, $('.js-table-body'), parseInt(appState.get("titleId")), currentCountElements);
    }
}


