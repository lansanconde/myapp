package myapp.beans;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful(name = "counter")
@LocalBean
public class CounterManager {

    private int counter = 0;

    public String count() {
        counter++;
        return String.format("counter is %d for %s", counter, this);
    }

    @PostConstruct
    public void start() {
        System.err.printf("++ start %s\n", this);
    }

    @Remove
    public void stop() {
        System.err.printf("++ stop %s\n", this);
    }

}