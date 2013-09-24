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
var processing = false;
var nicescroll;
Backbone.history.start();
var messageTails = {};

$(function(){
    nicescroll = $("html").niceScroll();
    $(document).scroll(function() {
        if (processing) {
            return false;
        }
        if ($(window).scrollTop() >= ($(document).height() - $(window).height())*0.75) {
            processing = true;
            if (appState.get("state") == "topics") {
                appendTopics(url, $('.js-table-body'), currentCountElements, orderTitles);
            }
            else {
                appendMessages(url, $('.js-table-body'), parseInt(appState.get("titleId")), currentCountElements);
            }
        }
    });
});