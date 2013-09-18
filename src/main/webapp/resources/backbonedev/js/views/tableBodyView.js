var TableBodyView = Backbone.View.extend({
    el: $('#table-body-container'),

    initialize: function () {
        appState.bind('change', this.render, this);
        this.render();
    },

    /*templates: {
        "topics": _.template($('#topic-table-body').html()),
        "messages": _.template($('#message-table-body').html())
    },*/

    render: function() {
        /*var state = appState.get("state");
        this.$el.html(this.templates[state](appState.toJSON()));*/
        return this;
    }
});

var tableBodyView = new TableBodyView();