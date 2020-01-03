package monapp;

import javax.faces.component.UIComponent;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class MyActionListener implements ActionListener {

    @Override
    public void processAction(ActionEvent evt) throws AbortProcessingException {
        UIComponent c = evt.getComponent();
        System.out.println("LOG: actionEvent sur " + c);
        System.out.println("LOG: actionEvent attribute = "
                + c.getAttributes().get("forActionlistener"));
    }

}