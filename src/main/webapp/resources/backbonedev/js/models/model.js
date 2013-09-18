//state : {'topics', 'messages'}
var AppState = Backbone.Model.extend({
    initialize: function() {
        this.set({state: 'topics'});
        this.set({titleId: 0});
    }
});

var appState = new AppState();