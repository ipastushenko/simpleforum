package it.sevenbits.jsonmodels;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sevenbits
 * Date: 9/23/13
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class JsonMessagePage<T> extends JsonPage<T> {
    public JsonMessagePage (final Long endDate, final List<T> elements, final String nameTitle) {
        super(endDate, elements);
        this.nameTitle = nameTitle;
    }

    public String getNameTitle() {
        return nameTitle;
    }

    public void setNameTitle(String nameTitle) {
        this.nameTitle = nameTitle;
    }

    private String nameTitle;
}
