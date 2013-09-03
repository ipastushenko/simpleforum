package it.sevenbits.controllers;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: sevenbits
 * Date: 9/2/13
 * Time: 3:32 PM
 * To change this template use File | Settings | File Templates.
 */

public class SendMessageForm {
    public void setPage(Long page) {
        this.page = page;
    }

    public Long getPage() {
        return page;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    public Long getTitleId() {
        return titleId;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getTextMessage() {
        return textMessage;
    }

    @NotNull
    private Long page;
    @NotNull
    private Long titleId;
    @NotNull
    @NotEmpty(message = "Password must not be blank.")
    private String textMessage;
}
