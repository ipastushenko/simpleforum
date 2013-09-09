package it.sevenbits.entity;

/**
 * Message
 * @author Ivan Pastushenko @ sevenbits
 * @version 1.0.0 30.08.2013
 */
public class Message {
    private Title title;
    private String textMessage;

    /**
     * Create new message
     */
    public Message() {
    }

    /**
     * Create new message
     * @param title title of message
     * @param textMessage text of message
     */
    public Message(final Title title, final String textMessage) {
        this.title = title;
        this.textMessage = textMessage;
    }

    /**
     * Get text of message
     * @return message
     */
    public String getTextMessage() {
        return textMessage;
    }

    /**
     * Get title of message
     * @return title
     */
    public Title getTitle () {
        return title;
    }

    /**
     * Set text of message
     * @param textMessage text
     */
    public void setTextMessage(final String textMessage) {
        this.textMessage = textMessage;
    }

    /**
     * Set title of message
     * @param title title
     */
    public void setTitle(final Title title) {
        this.title = title;
    }
}
