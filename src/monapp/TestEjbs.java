package monapp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class TestEjbs {
	
	
	@EJB
    EJBTraitement tm;
	
	@EJB
    EJBAccee accee;
	
	
	@EJB
	EJBGestionConnexion gestConnect;
	
    User user;
    
	Person p1;
	Person p2;
	Person p3;
	CurriculumVitae cv;
	CurriculumVitae cv1;
    
    public TestEjbs() throws NamingException {
    	EJBContainer.createEJBContainer().getContext().bind("inject", this);
    }
    
    @Before
    public void setUp() throws Exception {
    	
    	user =  new User();

    	// personne 1
    	p1 = new Person();
    	p1.setBirthDay("18/02/1989");
    	p1.setEmail("latty@gmail.com");
    	p1.setFirstName("latty");
    	p1.setName("FALL "+System.currentTimeMillis());
    	p1.setPassword("123");
    	p1.setWebSite("wwww.com");
    	
    	user.setEmail(p1.getEmail());
    	user.setPassword(p1.getPassword());
    	assertTrue(this.gestConnect.login(user, p1.getId(), "123"));
    	
    	
    	// creation d'un cv
    	cv = new CurriculumVitae();
    	cv.setTitle("recherceh stage");
    	cv.setAddressWeb("www.cv.com");
    	cv.setDescription("c'est mon cv");
    	cv.setExperiencePro("2 ans expériences");
    	cv.setProjects("projet algo");
    	cv.setFormation("master info");
    	cv.setOther("sport, music ");
    	cv.setYear(2019);
    	
    	//tp.login(user, p1.getId(), "123");
    	cv1 = new CurriculumVitae();
    	cv1.setTitle("profesionnel");
    	cv1.setAddressWeb("www.cv.com");
    	cv1.setDescription("c'est mon cv");
    	cv1.setExperiencePro("2 ans expériences");
    	cv1.setProjects("projet algo");
    	cv1.setFormation("master info");
    	cv1.setOther("sport, music "+System.currentTimeMillis());
    	cv1.setYear(2019);
    	
    
    }

    @After
    public void tearDown() throws Exception {
        EJBContainer.createEJBContainer().close();
    }

    @Test
    public void testCreatePerson() {
    	// personne 2
    	p2 = new Person();
    	p2.setBirthDay("18/02/1989");
    	p2.setEmail("aziz@gmail.com");
    	p2.setFirstName("latty "+System.currentTimeMillis());
    	p2.setName("aziz");
    	p2.setPassword("123");
    	p2.setWebSite("wwww.com");
    	

    	p1.setCv(cv);
    	
    	p1.addPerson(p2);
    	
    	Person p = tm.CreatePerson(user, p1);
    	assertEquals(p.getId(),p1.getId());
       
    }
    
    @Ignore
    @Test
    public void testUpdatePerson() {
    	
    	p1.setName("Chriss");
    	Person p = tm.updatePerson(user, p1);
    	assertEquals(p1.getName(), "Chriss");
    
    
    }
   
    @Ignore
    @Test
    public void TestdeletePersonne() {
    	p3 = new Person();
    	p3.setBirthDay("18/02/1989");
    	p3.setEmail("latty@gmail.com");
    	p3.setFirstName("latty");
    	p3.setName("FALL " +System.currentTimeMillis());
    	p3.setPassword("123");
    	p3.setWebSite("wwww.com");
    	
    	p1.addPerson(p3);
    	
    	tm.CreatePerson(user, p1);
    	
    	assertEquals(tm.deletePersonne(user, p3), null);
    	
    	
    }
    @Ignore
    @Test
    public void TestFindPerson() {
    	Person p = tm.CreatePerson(user, p1);
    	Person  pTm = accee.findPerson(p.getId());
    	assertEquals(pTm.getName(), p.getName());
    }
    @Ignore
    @Test
    public void TestFindAllPerson() {
    	tm.CreatePerson(user, p1);
    	List<Person>  pTm = accee.findAllPersons();
    	if(pTm!=null) {
    		for(Person p :pTm) {
        		assertNotNull(p);
        	}
    	}
    }
   
    /*  Test pour traiment CVs  */
    
    @Ignore
    @Test
    public void testCreateCv() {
    	CurriculumVitae cvTemp = tm.CreateCV(user, cv1);
    	assertEquals(cvTemp.getId(), cv1.getId());
    	
    }
    
    @Ignore
    @Test
    public void testDeletecV() {
    	// suppression du cv
    	tm.CreateCV(user, cv1);
    	CurriculumVitae cvTemp = tm.deleteCV(user, cv1);
    	assertEquals(cvTemp, null);
    }
    
    @Ignore
    @Test
    public void testUpdateCv() {
 
    	tm.CreateCV(user, cv);
    	cv.setTitle("Recherche d'emploi");
    	CurriculumVitae cvTemp = tm.updateCv(user, cv); 
    	assertEquals(cvTemp.getTitle(), "Recherche d'emploi");
    }   
    @Ignore
    @Test
   public void testFindCv() {
    	tm.CreateCV(user, cv);
    	CurriculumVitae cvtemp = accee.findCV(cv.getId());
    	assertEquals(cvtemp.getFormation(), cv.getFormation());
   }   
   

}