package it.sevenbits.jsonmodels;

import java.util.Date;
import java.util.List;

/**
 * Json page messages model
 * @author Ivan Pastushenko @ sevenbits
 * @version 1.0.0 06.09.2013
 */
public class JsonPage<T> extends JsonBase {
    private static final long serialVersionUID = -3741716781143989506L;
    private Long endDate;
    private List<T> elements;

    public JsonPage(final Long endDate, final List<T> elements) {
        super(true);
        this.endDate = endDate;
        this.elements = elements;
    }

    public Long getEndDate() {
        return endDate;
    }

    public List<T> getElements() {
        return elements;
    }

    public void setEndDate(final Long endDate) {
        this.endDate = endDate;
    }

    public void setElements(final List<T> elements) {
        this.elements = elements;
    }
}
