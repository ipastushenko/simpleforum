var TableHeadView = Backbone.View.extend({
    el: $('.js-table-head'),

    initialize: function () {
        appState.bind('change', this.render, this);
        this.render();
    },

    templates: {
        "topics": _.template($('#topic-table-head').html()),
        "messages": _.template($('#message-table-head').html())
    },

    render: function() {
        var state = appState.get("state");
        this.$el.html(this.templates[state](appState.toJSON()));
        return this;
    }
});

var tableHeadView = new TableHeadView();