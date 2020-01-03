package myapp.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless(name = "helloManager")
@LocalBean()
public class HelloManager {

    @Resource
    private SessionContext context;

    public String sayHello() {
        return "HelloManager " + System.currentTimeMillis();
    }

    @PostConstruct
    public void start() {
        System.err.printf("++ start %s\n", this);
    }

    @PreDestroy
    public void stop() {
        System.err.printf("++ stop %s\n", this);
    }

    public CounterManager newCounterManager() {
        return (CounterManager) context.lookup("java:/openejb/local/counterLocalBean");
    }

}