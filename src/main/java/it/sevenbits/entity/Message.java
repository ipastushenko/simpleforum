package it.sevenbits.entity;

/**
 * Created with IntelliJ IDEA.
 * User: sevenbits
 * Date: 8/30/13
 * Time: 1:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Message {
    public Message() {
    }

    public String getTextMessage() {
        return textMessage;
    }

    public Title getTitle () {
        return title;
    }

    public void setTextMessage(final String textMessage) {
        this.textMessage = textMessage;
    }

    public void setTitle(final Title title) {
        this.title = title;
    }

    private Title title;
    private String textMessage;
}
