package myapp.test;

import static org.junit.Assert.assertNotNull;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myapp.cdi.Home;
import myapp.cdi.HomeFactory;
import myapp.cdi.Person;
import myapp.cdi.Place;

public class TestCdi {

    static EJBContainer container;

    @BeforeAll
    static public void beforeAll() throws Exception {
        container = EJBContainer.createEJBContainer();
    }

    @AfterAll
    static public void afterAll() throws Exception {
        container.close();
    }

    @BeforeEach
    public void before() throws Exception {
        container.getContext().bind("inject", this);
    }

    @AfterEach
    public void after() throws Exception {
        container.getContext().unbind("inject");
    }

    @Inject
    Person p;
    
    @Inject
    @Place(country = "USA", town = "New-York")
    Home home1;
    
    @Inject
    @Place(country = "Chine", town = "Pekin")
    Home home2;
    @Test
    public void testPerson() {
        assertNotNull(p);
        assertNotNull(p.getHome());
    }

}