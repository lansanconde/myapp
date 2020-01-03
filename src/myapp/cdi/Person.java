package myapp.cdi;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped 
@Named("cdiPerson")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    static private int counter = 0;

    private int id = (++counter);

    private String name;
   
    @Inject
    @Place(country = "France", town = "Marseille")
    private Home home;
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name +
        ", home=" + home + "]";
    }
    
    @PostConstruct
    public void init() {
    	System.out.println(this);
    }

}