var TableBodyView = Backbone.View.extend({
    el: $('.js-table-body-container'),

    initialize: function () {
        appState.bind('change', this.render, this);
        this.render();
    },

    events : {
        "scroll" : "detect_scroll"
    },

    render: function() {
        if (appState.get("state") == 'topics') {
            updateTopics(url, this.$('.js-table-body'), currentCountElements, orderTitles);
        }
        else {
            updateMessages(url, this.$('.js-table-body'), parseInt(appState.get("titleId")), currentCountElements);
        }
        return this;
    },

    detect_scroll: function() {
         if (appState.get("state") == 'topics') {
             scrollTopic(url, this.$el);
         }
         else {
             scrollMessage(url, this.$el);
         }
    }
});

var tableBodyView = new TableBodyView();