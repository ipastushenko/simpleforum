var Views = { };

Controller = Backbone.Router.extend({
    routes: {
        "": "topic",
        "!/": "topic",
        "!/message": "message"
    },

    topic: function () {
        if (Views.topic != null) {
            Views.topic.render();
        }
    },

    message: function () {
        if (Views.message != null) {
            Views.message.render();
        }
    }
});

var controller = new Controller();

MessageView = Backbone.View.extend({
    initialize: function(){
        this.render();
    },
    render: function(){
        var template = _.template( $("#message_template").html(), {} );
        this.$el.html( template );
        return this;
    }
});

TopicView = Backbone.View.extend({
    initialize: function(){
        this.render();
    },
    render: function(){
        var template = _.template( $("#topic_template").html(), {} );
        this.$el.html( template );
        return this;
    },
    events: {
        "click input[type=button]": "toMessages"
    },
    toMessages: function( event ){
        controller.navigate("message", true);
    }
});

$(function () {
    Views = {
        topic: new TopicView({ el: $("#container") }),
        message: new MessageView({ el: $("#container") })
    }

    Backbone.history.start();
});