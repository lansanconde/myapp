/*
 * Ejb de gestion de connexion
 * 
 */

package monapp;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful(name = "gestionConnexion", description = "EJB de gestion de connexion")
//@TransactionManagement(TransactionManagementType.CONTAINER)
public class EJBGestionConnexion {
	
	   
    /*
     * Déclaration de l'entité menager pour le traiatement des données
     */
	 @PersistenceContext(unitName = "myData")
	 EntityManager em;
  
	 /*
	  * costructeur de la classe
	  */
    public EJBGestionConnexion() {
    	super();
    	
    }
    /*
     * Méthode de connexion
     * @return : false si connexion à échouer
     * @return : true si connexion à reussie
     */
    public boolean login(User user, long personId, String password) {
    	/*if( checkUser(user)) {
    		return true;
    	}*/
		Person person = em.find(Person.class, personId);
		if(person==null) {
			return false;
		}
		if(!person.getPassword().equals(password)) {
			return false;
		}
		
		
		
		String email = person.getEmail();
		user.setEmail(email);
		user.setPassword(password);
		
		
		System.out.println("Utilsateur connecté avec le email: "+person.getEmail());
		return true;
	}
    /*
     * Méthode de déconnexion
     */
    @Remove
    public void logOut(User user ) {
		user.setEmail("");
		user.setPassword("");
		user.setAdmin("");
		System.out.println("Utilsateur déconnecté");
		
	}
    
    /*
     * Méthode de vérification si l'utilsateur est identifié
     * @return : true si l'utilisateur est identifié
     * @return : false si l'utilisateur non identifié
     */
    public boolean checkUser(User user) {
		if(user.getEmail()!=null && user.getPassword()!=null) {
				return true;
			}
			System.out.println("Utilisateur non identifier  ");
			return false;
		
	}


}