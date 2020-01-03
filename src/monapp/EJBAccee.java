/*
 * Ejb  pour accéder aux données
 */

package monapp;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless(name = "ejbAccee", description = "EJB pour accéder et rechercher des personnes et des Cvs ")
//@TransactionManagement(TransactionManagementType.CONTAINER)
public class EJBAccee {

	/*
	 * Déclaration de l'entité menager pour le traiment des données
	 */
	 @PersistenceContext(unitName = "myData")
	 EntityManager em;

	
	/*	
	 * 	Méthode qui retourne un CV
	 * @param : id d'un cv
	 * @return : chercher est retourné le cv correspondant à l'id
	 */
	public CurriculumVitae findCV(Long n) {
		return em.find(CurriculumVitae.class, n);
	}
	
	/*
	 * Méthode qui retourne la liste de tout les CVs
	 * @return : liste des CVs
	 */
	public List<CurriculumVitae> findAllCv() {
		// TODO Auto-generated method stub
		 return em.createQuery("Select cv From CurriculumVitae cv", CurriculumVitae.class)
			          .getResultList();
	}
	/*
	 * Méthode qui retourne la personne avec l'id personId
	 * @param : id de la personne
	 * @return : la personne correspondante à l'id 
	 */
	public Person findPerson(Long personId) {
		return em.find(Person.class, personId);
	}
	/*
	 * Méthode qui retourne toutes les personnes 
	 * @return : liste des personnes
	 */
	public List<Person> findAllPersons() {
		// TODO Auto-generated method stub
		 return em.createQuery("Select p From Person p", Person.class)
	                .getResultList();
	}
	
	
}