package it.sevenbits.jsonmodels;

import java.io.Serializable;

/**
 * Json base model
 * @author Ivan Pastushenko @ sevebits
 * @version 1.0.0 06.09.2013
 */

public class JsonBase implements Serializable {
    private static final long serialVersionUID = 1053534909133755022L;

    private boolean success;

    public JsonBase(final boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }
}
