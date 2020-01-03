package monapp;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;



@ManagedBean
@ViewScoped
public class AjaxBean implements Serializable {

    private static final long serialVersionUID = 5443351151396868724L;

    private String text = "";
    List<String> cities = new LinkedList<String>();

    public AjaxBean() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        System.err.println("setText1 with " + text);
    }

    public String getNow() {
        return new Date().toString();
    }
    public void toUpper(AjaxBehaviorEvent event) {
        text = text.toUpperCase();
    }
    // traitement complete  en Ajax
    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public void addCity() {
        if (text.trim().length() > 0) {
            cities.add(text);
            System.err.println("add " + text);
            text = "";
        }
    }

    public void removeCity(int index) {
        cities.remove(index);
    }
    public void updateCity(int index) {
    	String ville  = cities.get(index);
    	//ville = 
    }

}