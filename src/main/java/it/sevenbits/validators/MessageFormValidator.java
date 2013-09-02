package it.sevenbits.validators;

import it.sevenbits.controllers.SendMessageForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: sevenbits
 * Date: 9/2/13
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */

public class MessageFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(SendMessageForm.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        SendMessageForm sendMessageForm = (SendMessageForm)obj;
        ValidationUtils.rejectIfEmpty(errors, "message", "message.empty");
        if (sendMessageForm.getTitleId() == null)
            errors.reject("titleId", "null");
        if (sendMessageForm.getPage() == null)
            errors.reject("page", "null");
    }
}
