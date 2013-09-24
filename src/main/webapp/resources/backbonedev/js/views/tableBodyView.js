var TableBodyView = Backbone.View.extend({
    el: $('.js-table-body-container'),

    initialize: function () {
        appState.bind('change', this.render, this);
        this.render();
    },

    events : {
        "click .js-begin-message" : "open_message"
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

    open_message: function(event) {
        var id = event.target.getAttribute('id');
        $('.' + id).css('display', 'block');
        $('.' + id + '-begin').css('margin-bottom', '0');
        $('.' + id + '-begin').css('white-space', 'normal');
        $('#' + id).css('margin-bottom', '0');
        $('#' + id).css('display', 'none');
    }
});

var tableBodyView = new TableBodyView();