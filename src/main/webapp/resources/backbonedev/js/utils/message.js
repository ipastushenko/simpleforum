function appendMessage(url, tbody, createtime, name, id, end) {
    tbody.append('<tr>' +
                    '<td class="span12">' +
                        '<p class="message-text-body">' +
                            '<span class="arial text-tbody time-message">'+ createtime.toLocaleString() + '</span>' +
                            '<span class="underline-message arial text-delete" onclick="deleteMessage(' + url + ',' + id +')">' + 'delete message' + '</span>' +
                        '</p>' +
                    '</td>' +
                 '</tr>');
    tbody.append('<tr>' +
                    '<td class="span12 ' + end + '">' +
                        '<p class="arial text-message">' + name + '</p>' +
                    '</td>' +
                 '</tr>');
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