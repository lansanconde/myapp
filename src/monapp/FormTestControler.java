package monapp;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.Past;

@ManagedBean(name = "formTest")
@SessionScoped
public class FormTestControler {

    private String text = "X";
    
    @Past(message = "Trop récent !")
    private Date birthday = new Date();
    
   // private Double number = 100.0;

    public String submit() {
        System.out.println("LOG: Submit");
        return null;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        System.out.println("LOG: Set text with " + text);
    }
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
        System.out.println("LOG: Set birthday with " + birthday);
    }
//    public Double getNumber() {
//        return number;
//    }
//
//    public void setNumber(Double number) {
//        this.number = number;
//        System.out.println("LOG: Set number with " + number);
//    }
//    public void numberChanged(ValueChangeEvent e) {
//        System.out.println("LOG: old number = " + e.getOldValue());
//        System.out.println("LOG: new number = " + e.getNewValue());
//    }
    public void setParameter(String value) {
        System.out.println("LOG: Fix parameter with " + value);
    }

    public void myListener(ActionEvent evt) {
        UIComponent c = evt.getComponent();
        System.out.println("LOG: method actionEvent sur " + c);
    }



}