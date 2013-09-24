function appendMessage(url, tbody, createtime, name, id, end) {
    var beginName = '';
    var endName = '';
    var firstEnter = name.indexOf('\n', 0);
    if (firstEnter == -1) {
        beginName = name;
    }
    else {
        beginName = name.substring(0, firstEnter);
        if (firstEnter != name.length - 1)
            endName = name.substring(firstEnter + 1);
    }
    endName = endName.replace(/\n/g, "<br>");
    tbody.append('<tr>' +
                    '<td class="span12">' +
                        '<p class="message-text-body">' +
                            '<span class="arial text-tbody time-message">'+ createtime.toLocaleString() + '</span>' +
                            '<span class="underline-message arial text-delete" onclick="deleteMessage(' + url + ',' + id +')">' + 'delete message' + '</span>' +
                        '</p>' +
                    '</td>' +
                 '</tr>');
    if (endName == '' && beginName.length < 63) {
        tbody.append('<tr>' +
            '<td class="' + end + '">' +
                '<div class="js-message-' + id + '-begin begin-message crop span11 arial text-message">' + beginName + '</div>' +
            '</td>' +
            '</tr>');
    }
    else {
        tbody.append('<tr>' +
                        '<td class="' + end + '">' +
                            '<div class="js-message-' + id + '-begin begin-message crop span11 arial text-message">' + beginName + '</div>' +
                            '<div class="show-message" >' +
                                '<img id="js-message-' + id + '" class="js-begin-message" src="'+ url +'/resources/backbonedev/img/open_message_03.png">' +
                            '</div>' +
                            '<div class="js-message-' + id +' end-message crop span11 arial text-message">' + endName + '</div>' +
                        '</td>' +
                     '</tr>');
    }
}

function appendListMessages(url, data, tbody) {
    currentCountMessages += data.elements.length;
    if (data.nameTitle == null)
        data.nameTitle = 'topic deleted';
    $('.js-topic-name-text').html(data.nameTitle);
    var message = 'message';
    if (data.elements.length != 1) {
        message = 'messages';
    }
    $('.js-count-messages').html(currentCountMessages + ' ' + message);
    var end = 'text-message-row';
    for (var i in data.elements) {
        if (i == data.elements.length - 1) {
            end = '';
        }
     appendMessage(url,
             tbody,
             new Date(data.elements[i].createDate),
             data.elements[i].textMessage,
             data.elements[i].id,
             end
     );
    }
}