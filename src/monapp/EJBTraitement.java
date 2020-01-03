/*
 * Ejb de traitelent de personnes
 */
package monapp;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful(name = "ejbTraitement", description = "EJB de traiment des personnes et des Cvs")
//@TransactionManagement(TransactionManagementType.CONTAINER)
public class EJBTraitement {
	
	/*
	 * Déclaration de l'entité menager
	 */
	 @PersistenceContext(unitName = "myData")
	 EntityManager em;
	 /*
	  * Déclaration de l'Ejb de connexion
	  */
	 @EJB 
	 EJBGestionConnexion gestConect;
	    
	
	 
	 /*
	  *  Methode de création  de Personnes
	  *  @param : un utilisateur et une personne
	  *  @return : la personne après création
	  *  @return : null si la création à échouer
	  */
	public Person CreatePerson(User user, Person p) {
		if(gestConect.checkUser(user)) {
			if (p.getId() == null) {
	            em.persist(p);
	        } else {
	            p = em.merge(p);
	        }
			System.out.println("Personn Enregistré avec id: "+ p.getId());
			 return p;
		}
		System.out.println("Personn Non Enregistré" );
		return null;
	}
	/*
	 * Méthode de mise à jour d'une personne
	 * @param : un utilisateur te une personne
	 * @return : la personne après la mise en jour
	 * @return: null si la mise ne jour à échouer
	 */
	public Person  updatePerson(User user, Person p) {
		if(gestConect.checkUser(user)) {
			p = em.merge(p);
	        em.persist(p); 
	        return  p;
		}
		return null;
       
	}
	/*
	 * Méthode de suppression d'une personne
	 * @param : un utilisateur et une personne
	 * @return : null si la suppression a reussie
	 * @return : la personne à supprimer si la suppression à échouer
	 */
	public Person deletePersonne(User user, Person p) {
		Person ptemp = p;
		if(gestConect.checkUser(user)) {
			p = em.merge(p);
	        em.remove(p);
	        ptemp= em.find(Person.class, ptemp.getId());
	        return ptemp;
		}
		return ptemp;
	}
	/*
	 * Méthode de création de CV
	 * @param : un utilisateur et un CV
	 * @return : le cv crée
	 * @return : null si la création à échouer
	 */
	public CurriculumVitae CreateCV(User user, CurriculumVitae cv) {
		if(gestConect.checkUser(user)) {
			if (cv.getId() == null) {
	            em.persist(cv);
	        } else {
	        	cv = em.merge(cv);
	        }
		return cv;
		}
		return null;
			
	}
	/*
	 * Méthode de suppression d'un CV
	 * @param : un utilisateur et un CV
	 * @return : null si la suppression a reussie
	 * @return : à échouer
	 */
	public CurriculumVitae deleteCV(User user, CurriculumVitae cv) {
		CurriculumVitae cvtmp = cv;
		if(gestConect.checkUser(user)) {
			cv = em.merge(cv);
	        em.remove(cv);
	        return em.find(CurriculumVitae.class, cvtmp.getId());  
		}
		return cvtmp;
			
	}
	/*
	 * Méthode qui permet la mise à jour d'un cv
	 * @param : un utilisateur et un cv
	 * @return : null si la mise à jour à échouer
	 * @return : le cv après mise à jour
	 */
	public CurriculumVitae  updateCv(User user, CurriculumVitae cv) {
		if(gestConect.checkUser(user)) {
			cv = em.merge(cv);
	        em.persist(cv); 
	        return cv;
		}
		return null;
	}
	


}