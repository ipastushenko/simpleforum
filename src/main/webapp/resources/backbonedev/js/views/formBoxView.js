var FormBoxView = Backbone.View.extend({
    el: $('.js-form-box'),

    initialize: function () {
        appState.bind('change', this.render, this);
        this.render();
    },

    templates: {
        "topics": _.template($('#topic-form-box').html()),
        "messages": _.template($('#message-form-box').html())
    },

    render: function() {
        var state = appState.get("state");
        this.$el.html(this.templates[state](appState.toJSON()));
        return this;
    },
    events: {
        "click .js-send-message-btn": "send",
        "click .js-create-topic-btn": "create"
    },
    send: function() {
        clickCreateNewMessage(url, this.$('.js-message-form'))
        return false;
    },
    create: function() {
        clickCreateNewTopic(url, this.$('.js-topic-form'))
        return false;
    }
});

var formBoxView = new FormBoxView();