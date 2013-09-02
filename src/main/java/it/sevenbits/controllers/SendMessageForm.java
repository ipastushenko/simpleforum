package it.sevenbits.controllers;

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

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


    private Long page;
    private Long titleId;
    private String message;
}
