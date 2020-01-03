package monapp;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("myconstraints.Hello")
public class HelloValidator implements Validator {

    @Override
    public void validate(FacesContext ct, UIComponent comp, Object obj)
            throws ValidatorException {
        System.out.println("LOG: HelloValidator on " + comp);
        String value = obj.toString();
        if (!value.contains("hello")) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "hello absent", "Il manque le mot 'hello'");
            throw new ValidatorException(msg);
        }
    }

}