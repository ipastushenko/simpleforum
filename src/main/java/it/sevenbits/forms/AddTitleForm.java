package it.sevenbits.forms;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Form for add new title.
 * @author Ivan Pastushenko @ sevenbits
 * @version 1.0.0 04.09.2013
 */
public class AddTitleForm {
    /**Name of new title*/
    @NotNull
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
