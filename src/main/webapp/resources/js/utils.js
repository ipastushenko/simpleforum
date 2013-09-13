function hiddenObject(object) {
    object.css('visibility', 'hidden');
    object.css('position', 'absolute');
}

function showObject(object) {
    object.css('position', 'relative');
    object.css('visibility', 'visible');
}

function swap(objectToHidden, objectToVisible) {
    hiddenObject(objectToHidden);
    showObject(objectToVisible);
}