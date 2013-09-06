package it.sevenbits.jsonmodels;

import java.util.List;

/**
 * Json error model
 * @author Ivan Pastushenko @ sevenbits
 * @version 1.0.0 06.09.2013
 */
public class JsonError extends JsonBase {
    private static final long serialVersionUID = -7577709044832862434L;
    private List<String> errors;

    public JsonError(final List<String> errors) {
        super(false);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors (final List<String> errors) {
        this.errors = errors;
    }
}
