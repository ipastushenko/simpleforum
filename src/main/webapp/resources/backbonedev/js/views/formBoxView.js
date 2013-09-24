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
        "keypress .js-message-text" : "key_press",
        "click .js-send-message-btn": "send",
        "click .js-create-topic-btn": "create"
    },

    key_press: function (event) {
        var keyCode = (event.which ? event.which : event.keyCode);
        if (keyCode === 10 || keyCode == 13 && event.ctrlKey) {
            this.send();
        }
        return true;
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