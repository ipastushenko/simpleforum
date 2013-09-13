function appendMessage(url, tbody, createtime, name, id) {
    tbody.append('<tr>');
    tbody.append('<td class="span3"><p class="text-center">'+ createtime + '</p></td>');
    tbody.append('<td class="span8"><p class="text-left">' + name + '</p></td>');
    tbody.append('<td class="span1"><p class="text-center"><button class="btn" onclick="deleteMessage(' + url + ',' + id +')">'+ 'delete' +'</button></p></td>');
    tbody.append('</tr>');
}

function appendListMessages(url, data, tbody) {
     for (var i in data.elements) {
         appendMessage(url,
                 tbody,
                 new Date(data.elements[i].createDate),
                 data.elements[i].textMessage,
                 data.elements[i].id
         );
     }
}