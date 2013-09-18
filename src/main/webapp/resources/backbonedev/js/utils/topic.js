function appendTopic(url, tbody, createtime, changetime, name, id) {
    tbody.append('<tr>');
    tbody.append('<td class="span3"><p class="text-center">'+ createtime + '</p></td>');
    tbody.append('<td class="span3"><p class="text-center">'+ changetime + '</p></td>');
    tbody.append('<td class="span5"><a href="#" onclick="toMessages(' + url + ',\'' + name + '\',' + id + ')">'+ name + '</a></td>');
    tbody.append('<td class="span1"><p class="text-center"><button class="btn" onclick="deleteTopic(' + url + ',' + id +')">'+ 'delete' +'</button></p></td>');
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