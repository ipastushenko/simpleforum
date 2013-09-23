function appendTopic(url, tbody, createtime, changetime, name, id) {
    tbody.append('<tr>');
    tbody.append('<td class="span3"><p class="text-center text-tbody arial">'+ createtime.toLocaleString() + '</p></td>');
    tbody.append('<td class="span3"><p class="text-center text-tbody arial">'+ changetime.toLocaleString() + '</p></td>');
    tbody.append('<td class="span5">' +
                    '<p class="topic-name-body">' +
                        '<a href="#messages/' + id + '">' +
                            '<span class="text-tbody arial bold-text underline-body">'+ name + '</span>' +
                        '</a>' +
                    '</p>' +
                 '</td>'
    );
    tbody.append('<td class="span1">' +
                    '<p><button class="btn-color delete-topic-btn" onclick="deleteTopic(' + url + ',' + id +')">' +
                        '<img class="image-delete-btn" src='+ url +'/resources/backbonedev/img/delete_03.png' + '>' +
                    '</button></p>' +
                 '</td>'
    );
    tbody.append('</tr>');
}

function appendListTopics(url, data, tbody) {
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