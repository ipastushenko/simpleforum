var orderTitles = 0;
var currentDate = 0;
var currentCountElements = 15;
var currentCountMessages = 0;
var timeWarning = 500;
var url = prefixUrl();
var warningRun = false;

function warning(elem, timeWarning) {
    if (warningRun == false) {
        warningRun = true;
        var cssOld = elem.css('border');
        elem.css('border', '2px solid #FF0000');
        setTimeout(function(){
                elem.css('border', cssOld);
                warningRun = false;
            },
            timeWarning
        );
    }
}

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
            warning($('.container'), timeWarning);
        }
        processing = false;
        nicescroll.resize();
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
            warning($('.container'), timeWarning);
        }
        processing = false;
        nicescroll.resize();;
    });
}

function deleteTopic(url, id) {
    processing = false;
    $.getJSON(url + 'json/removeTitle/' + id, {}, function(data) {
        if (data.success) {
            updateTopics(url, $('.js-table-body'), currentCountElements, orderTitles);
        }
        else {
            warning($('.container'), timeWarning);
        }
        processing = true;
    });
}

function deleteMessage(url, id) {
    processing = false;
    $.getJSON(url + 'json/removeMessage/' + id, {}, function(data) {
        if (data.success) {
            updateMessages(url, $('.js-table-body'), parseInt(appState.get("titleId")), currentCountElements);
        }
        else {
            warning($('.container'), timeWarning);
        }
        processing = true;
    });
}

function clickCreateNewMessage(url, form) {
    currentDate = 0;
    var textSerializable = {'titleId': parseInt(appState.get("titleId")), 'textMessage' : $.trim($('.js-message-text').val())};
    processing = false;
    $.postJSON(url+'json/messages', textSerializable, function(data) {
        if (data.success){
            $('.js-message-text').val('');
            updateMessages(url, $('.js-table-body'), parseInt(appState.get("titleId")), currentCountElements);
        }
        else {
            warning($('.js-message-text'), timeWarning);
        }
        processing = true;
    });
}

function clickCreateNewTopic(url, form) {
    currentDate = 0;
    var textSerializable = {'name' : $.trim($('.js-new-topic-name').val())};
    processing = false;
    $.postJSON(url+'json/titles', textSerializable, function(data) {
        if (data.success){
            $('.js-new-topic-name').val('');
            updateTopics(url, $('.js-table-body'), currentCountElements, orderTitles);
        }
        else {
            warning($('.js-new-topic-name'), timeWarning);
        }
        processing = true;
    });
}


