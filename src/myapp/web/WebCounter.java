package myapp.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import myapp.beans.CounterManager;

@SessionScoped
@Named("webCounter")
public class WebCounter implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB(name = "counter")
    private CounterManager counter;

    public CounterManager getCounter() {
        return counter;
    }

    public void setCounter(CounterManager counter) {
        this.counter = counter;
    }

    @PostConstruct
    public void start() {
        System.err.printf("++ start %s\n", this);
    }

    @PreDestroy
    public void stop() {
        counter.stop();
        System.err.printf("++ stop %s\n", this);
    }

}