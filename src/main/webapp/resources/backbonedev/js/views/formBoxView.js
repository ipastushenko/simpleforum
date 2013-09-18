var FormBoxView = Backbone.View.extend({
    el: $('#form-box'),

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
        "click #send-message-btn": "send",
        "click #create-topic-btn": "create"
    },
    send: function() {

        return false;
    },
    create: function() {
        controller.navigate("messages/1", true);
        return false;
    }
});

var formBoxView = new FormBoxView();