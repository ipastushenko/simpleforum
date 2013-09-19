var TopicNameView = Backbone.View.extend({
    el: $('.js-topic-name'),

    initialize: function () {
        appState.bind('change', this.render, this);
        this.render();
    },

    templates: {
        "topics": _.template($('#hide-topic-name').html()),
        "messages": _.template($('#show-topic-name').html())
    },

    render: function() {
        var state = appState.get("state");
        this.$el.html(this.templates[state](appState.toJSON()));
        $('.js-topic-name-text').html(appState.get("titleId"));
        return this;
    }
});

var topicNameView = new TopicNameView();