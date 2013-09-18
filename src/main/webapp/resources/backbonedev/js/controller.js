var Controller = Backbone.Router.extend({
    routes: {
        "": "topics",
        "topics": "topics",
        "messages/:titleId": "messages",
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