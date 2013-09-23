var TableBodyView = Backbone.View.extend({
    el: $('.js-table-body-container'),

    initialize: function () {
        appState.bind('change', this.render, this);
        this.render();
    },

    render: function() {
        if (appState.get("state") == 'topics') {
            updateTopics(url, this.$('.js-table-body'), currentCountElements, orderTitles);
        }
        else {
            updateMessages(url, this.$('.js-table-body'), parseInt(appState.get("titleId")), currentCountElements);
        }
        return this;
    }
});

var tableBodyView = new TableBodyView();