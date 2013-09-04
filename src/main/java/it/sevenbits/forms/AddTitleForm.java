package it.sevenbits.forms;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Form for add new title.
 * @author Ivan Pastushenko @ sevenbits
 * @version 1.0.0 04.09.2013
 */
public class AddTitleForm {
    /**Current page*/
    @NotNull
    private Long page;

    /**Name of new title*/
    @NotNull
    @NotEmpty
    private String name;

    public Long getPage() {
        return page;
    }

    public String getName() {
        return name;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public void setName(String name) {
        this.name = name;
    }
}
