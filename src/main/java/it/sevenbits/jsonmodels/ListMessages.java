package it.sevenbits.jsonmodels;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: sevenbits
 * Date: 9/5/13
 * Time: 4:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListMessages implements Serializable {
    private static final long serialVersionUID = -1423550071317022680L;
    public String text;
    String staffText[];

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getStaffText() {
        return staffText;
    }

    public void setStaffText(String[] staffText) {
        this.staffText = staffText;
    }
}
