package it.sevenbits.entity;

/**
 * Message
 * @author Ivan Pastushenko @ sevenbits
 * @version 1.0.0 30.08.2013
 */
public class Message {
    private Title title;
    private String textMessage;

    public Message() {
    }

    public Message(final Title title, final String textMessage) {
        this.title = title;
        this.textMessage = textMessage;
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
}
