package it.sevenbits.forms;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Form for add new message
 * @author Ivan Pastushenko @ sevenbits
 * @version 1.0.0 9/2/13
 */

public class SendMessageForm {
    /**Current title id*/
    @NotNull
    private Long titleId;

    /**New message*/
    @NotNull
    @NotEmpty
    private String textMessage;

    public Long getTitleId() {
        return titleId;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTitleId(final Long titleId) {
        this.titleId = titleId;
    }

    public void setTextMessage(final String textMessage) {
        this.textMessage = textMessage;
    }
}
