package it.sevenbits.jsonmodels;

import java.io.Serializable;
import java.util.Date;

public class JsonData implements Serializable{
    private static final long serialVersionUID = -5402943221724622943L;

    private Date date;
    private Long count;

    public JsonData(Date date, Long count) {
        this.count = count;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public Long getCount() {
        return count;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public void setCount(final Long count) {
        this.count = count;
    }
}
