package myapp.test;

import static org.junit.Assert.assertTrue;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import myapp.beans.HelloManager;

public class HelloManagerTest {

    @EJB
    HelloManager hm;

    public HelloManagerTest() throws Exception {
        EJBContainer.createEJBContainer().getContext().bind("inject", this);
    }

    @AfterAll
    static public void stop() throws Exception {
        EJBContainer.createEJBContainer().close();
    }

    @Test
    public void testhelloManager() {
        System.out.println(hm.sayHello());
        System.out.println(hm.newCounterManager().count());
        assertTrue(hm.newCounterManager().count().contains("is 1 "));
    }

}