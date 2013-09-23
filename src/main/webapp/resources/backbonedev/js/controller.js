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
Backbone.history.start();

$(function(){
    $("html").niceScroll();
    /*$(window).
    if ((objectScroll[0].scrollTop + objectScroll[0].clientHeight) / objectScroll[0].scrollHeight > 0.95) {
        appendTopics(url, $('.js-table-body'), currentCountElements, orderTitles);
    } */
});