var Controller = Backbone.Router.extend({
    routes: {
        "": "topics",
        "topics": "topics",
        "messages/:titleId": "messages"
    },

    topics: function() {
        appState.set({state:"topics"});
    },
    messages: function(titleId) {
        appState.set({state:"messages", titleId:titleId});
    }
});

var controller = new Controller();
var sem = false;
Backbone.history.start();

$(function(){
    var scroll = $("html").niceScroll();
    $("html").niceScroll().scrollend(function(info) {
        if (scroll.scrollvaluemax <= (scroll.scroll.y + 50)) {
            if (appState.get("state") == "topics") {
                appendTopics(url, $('.js-table-body'), currentCountElements, orderTitles);
            }
            else {
                appendMessages(url, $('.js-table-body'), parseInt(appState.get("titleId")), currentCountElements);
            }
        }
    });
});