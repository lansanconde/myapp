package monapp;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;


/*
 * Classe qui sert du controleur de l'application
 * @author: Latty FALL et Lansana CONDÉ
 * 
 */

@ManagedBean(name = "person")
@SessionScoped
public class PersonControler{
	
	/*
	 * ejb qui permet le traitement des données(création, suppression, modification, etc ...) 
	 */
	@EJB
    EJBTraitement tm;
	
	/*
	 * ejb qui permet d'accéder aux données (trouver un ou pluseiurs personnes/cv) 
	 */
	@EJB
    EJBAccee accee;
	/*
	 * ejb qui permet qui gère la connexion et déconnexion d'un utilsateur 
	 */
	@EJB
	EJBGestionConnexion gestConnect;

	
	/*
	 * personne en cours de traitement dans le pages jsp
	 */
    Person thePerson = new Person();
    /*
	 * variable qui permet d'ajouter une ou plusieurs personnes sur sa liste des personnes
	 */
    Person personAdmin = new Person();
    /*
	 * cv eb cours de traitement dans les pages jsp 
	 */
    CurriculumVitae theCv = new CurriculumVitae();
    /*
	 * l'utilsateur en cours d'utilsation 
	 */
    User user = new User();
    /*
	 * pour récuper l'id de la personne connecté en form de string dans la page jsp de login
	 */
	private  String personId;
	/*
	 * pour récuper l'id de la personne connecté en form de int dans le controleur
	 */
	private Long idConnected;
	
	/*
	 * 	Boolean pour tester si la personne et connecté ou pas
	 */
	private boolean testConnect = false;
	
	private String originalURL ="";
	
	/*
	 * 	Pour récuper le context de l'application pour la gestion des sessions de connexion
	 */
	private FacesContext context;
	/*
	 * pour récuper le context de l'application pour la gestion des sessions de connexion
	 */
	private ExternalContext externalContext;
	/*
	 * Pour réucper le mot de passe de l'utilsateur
	 */
	private  String password;
	/*
	 * 	pour récuper le nom de l'utisateur 
	 */
	private String username;
	/*
	 * @return :getter du mot de passe
	 */
    public String getPassword() {
		return password;
	}
    /*
     * Méthode de setter du mot passe
     * @param : le mot de passe
     */
	public void setPassword(String password) {
		this.password = password;
	}
	/*
	 * @return :getter de l'id de l'utilsateur
	 */
	public String getPersonId() {
		return personId;
	}
	/*
     * Méthode  setter de l'id de l'utisateur
     * @param : id de l'utilsateur
     */
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	/*
     * Méthode de getter de l'utilsateur
     * @return : le nom d'utilsateur
     */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/*
	 * Métode d'initialisation de l'application
	 * Initialisation de la personne administrateur de l'application
	 * Initialisation du context de session de l'utilsateur
	 */
	@PostConstruct
    public void init() {
		 externalContext = FacesContext.getCurrentInstance().getExternalContext();
	     originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);
	     
        System.out.println("Create first person ");
        
            Person p1 = new Person();
        	p1.setBirthDay("18/02/1989");
        	p1.setEmail("latty@gmail.com");
        	p1.setFirstName("latty");
        	p1.setName("FALL "+System.currentTimeMillis());
        	p1.setPassword("123");
        	p1.setWebSite("wwww.com");
        	 
        	user = new User();
        	user.setEmail(p1.getEmail());
        	user.setPassword(p1.getPassword());
        	user.setAdmin(p1.getFirstName()); 
        	
            tm.CreatePerson(user, p1);  
            
       
    }
	/*
	 * @return : la liste des personnes dans la base de données
	 */
    public List<Person> getPersons() {
        return accee.findAllPersons();
    }
    /*
     * @return : la liste des personne ajouter par la personne connecté
     */
    public Set<Person> getPersonAdmin(){
    	return personAdmin.getPersons(); 
    }    
    /*
     * @return : la liste des CVs dans la base de données
     */
    public List<CurriculumVitae> getCvs() {
        return accee.findAllCv();
    }
    /*
     * @return : la personne en cours de traitement
     */
    public Person getThePerson() {
        return thePerson;
    }
    /*
     * @return : le CV en cours de traitement
     */
    public CurriculumVitae getTheCv() {
        return theCv;
    }
    /*
     * @param : id d'un personne à retourner
     * @return : la personne avec id passé en paramêtre 
     */
    public Person getPerson(Long personId) {
        return accee.findPerson(personId);
    }
    /*
     * @param : id du cv à retourner
     * @return : CV avec id passeé en paramêtre
     */
    public CurriculumVitae getCv(Long personId) {
        return accee.findCV(personId);
    }
    /*
     * @param : id de la personne à visionner
     * @return : la page jsp pour visionner une personne
     */
    public String show(Long n) {
        this.thePerson = accee.findPerson(n);
        return "showPerson";
    }
    /*
     * @param : id du CV à visionner
     * @return  : la page jsp pour visionner le CV
     */
    public String showCv(Long n) {
        this.theCv = accee.findCV(n);
        return "showCv";
    }
    /*
     * @param : id d'une personne
     * @return : la page jsp pour visionner le CV d'une personne avec id passer en paramêtre
     */
    public String showCvWithPesonId(Long n) {
    	Person p = accee.findPerson(n);
        this.theCv = p.getCv();;
        return "showCv";
    }
    /* Méthode pour enregistrer une personne
     * @return : une page jsp pour visioner la personne après enrégistrement 
     * ou rester sur la page d'édition
     */
    public String save() {
    	personAdmin = accee.findPerson(idConnected);
    	personAdmin.addPerson(thePerson);
    	Person  p = tm.CreatePerson(user, personAdmin);
    	if(p==null) {
    		FacesContext ct = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(" Person could not be saved");
            ct.addMessage("test:enregistrement", msg);
            ct.validationFailed();
            return "editPerson";
    	}
	    return "showPerson";
    }
    /*
     *  Méthode pour enregistrer un CV
     *  @return : la page jsp pour visionner le CV ou rester sur la page d'édition
     */
    public String saveCv() {
    	personAdmin = accee.findPerson(idConnected);
    	if(personAdmin.getCv()==null) {
    		personAdmin.setCv(theCv);
    		Person  p = tm.CreatePerson(user, personAdmin);
    		if(p==null) {
        		FacesContext ct = FacesContext.getCurrentInstance();
                FacesMessage msg = new FacesMessage(" CV could not be saved");
                ct.addMessage("test:enregistrement", msg);
                ct.validationFailed();
                return "editCv";
        	}
    		return "showCv";
    	}
    	FacesContext ct = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage("You have already have cv");
        ct.addMessage("test:enregistrement", msg);
        ct.validationFailed();
 	    return "editCv";
     }
    /*
     * 	Méthode de création d'une nouvelle personne
     * @return : la page edition d'une personne ou rester sur la connexion
     */
    public String newPerson() throws IOException {
    	if(testConnect==false) {
    		FacesContext ct = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(" You shoud  be connected");
            ct.addMessage("test:edit Person", msg);
            ct.validationFailed();
            return  "login";
    	}
    	thePerson = new Person();
        return "editPerson";
    }
    /*
     * Méthode de création d'un nouveau CV
     * @return : la page d'édition ou rester sur la page de connexion
     */
    public String newCv() throws IOException {
    	theCv = new CurriculumVitae();
    	if(testConnect==false) {
    		FacesContext ct = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(" You shoud  be connected");
            ct.addMessage("test:edit CV", msg);
            ct.validationFailed();
    		return  "login";
    	}
    	return "editCv"; 
    }
    
    /*
     * Méthode qui permet la connexion d'un utilisateur
     * @return : la page contenant la liste des personnes et CVs ou bien rester sur la page de connexion
     */
    public String login() throws IOException{
    		idConnected = Long.parseLong(this.personId);
	  
	    	context = FacesContext.getCurrentInstance();
	        externalContext = context.getExternalContext();
	        //request = (HttpServletRequest) externalContext.getRequest();
	        try {
	            boolean connect = gestConnect.login(user, idConnected , this.password);
	            if(connect == false){
	                throw new Exception("Invalid user/password");
	            }
	            else {
		            externalContext.getSessionMap().put("user", user);
		            testConnect = true;
		            personAdmin = accee.findPerson(idConnected);
		            user.setAdmin(personAdmin.getFirstName());
	            	return "persons";
	            }
	        } catch (Exception e) {
	            context.addMessage(null, new FacesMessage("Unknown login"));
	        }        
	        return "login";
    	        
      }
    /*
     * Méthode de déconnexion
     * @return : la page contenant la liste des personnes et CVs
     */
    public String logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        gestConnect.logOut(user);
       // externalContext.redirect(externalContext.getRequestContextPath() + "/log.xhtml");
        return "persons";
    }
    /*
     * Méthode qui permet de vérifier si un utilsateur peut modifier une personne 
     * @return : page de connexion si non identitifié
     * @return : page de modification si la personne à modifier est sur sa liste de personnes
     * @return : page de visualisation de la personne si l'utilisateur ne peut pas modiifer la personne
     * @return 
     */
    public String checkModifyPerson() {   
	   if (testConnect == false) {
           FacesContext ct = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage("You must be connected before modify !!!");
           ct.addMessage("test:connexion", msg);
           ct.validationFailed();
           return "login";
       }
	   // tester si la personne est sur sa liste
	   // avant de passer à la modification
	   personAdmin = accee.findPerson(idConnected);
	   Set<Person> persons = getPersonAdmin();
	   for(Person p:persons) {
		   if(p.getId().equals(thePerson.getId()) || thePerson.getId().equals(personAdmin.getId()) )  return "updatePerson";
	   }
   	   FacesContext ct = FacesContext.getCurrentInstance();
       FacesMessage msg = new FacesMessage("You couldn't modify person: "+ thePerson.getFirstName());
       ct.addMessage("test:modify person", msg);
       ct.validationFailed();
       return "showPerson";
    }
    /*
     * Méthode qui gère la modification d'une personne
     * @return : la page d'édition si la personne est modifiable par l'utilsateur
     * @return : la page de visualisation si la personne n'est pas modifiable
     */
    public String updatePerson() {
    	Person  p = tm.updatePerson(user, thePerson);
    	if(p.equals(null)) {
    		FacesContext ct = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(" Person could not be saved");
            ct.addMessage("test:enregistrement", msg);
            ct.validationFailed();
            return "editPerson";
    	}
	    return "showPerson";
    }
    /*
     * Méthode qui permet de vérifier si un utilsateur peut modifier CV
     * @return : page de connexion si L'utilisateur non identitifié
     * @return : page de modification si le CV à modifier est son propre CV
     * @return : page de visualisation de le CV ne l'appartient pas 
     */
    public String checkModifyCv() {   
 	   if (testConnect == false) {
            FacesContext ct = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage("You must be connected before modify !!!");
            ct.addMessage("test:connexion", msg);
            ct.validationFailed();
            return "login";
        }
 	   Person p  = accee.findPerson(idConnected);
 	   if(p.getCv()!=null) {
	 	   if(p.getCv().getId()!=theCv.getId()){
	 		  FacesContext ct = FacesContext.getCurrentInstance();
	          FacesMessage msg = new FacesMessage("You could not modifiy: it's not your cv !!!");
	          ct.addMessage("test:modify", msg);
	          ct.validationFailed();
	          return "showCv";
	 	   }
	 	  return "updateCV";
 	   }
 	  FacesContext ct = FacesContext.getCurrentInstance();
      FacesMessage msg = new FacesMessage("You don't have cv to modifiy: connect and create a new cv");
      ct.addMessage("test:modify", msg);
      ct.validationFailed();
      return "showCv";
       
     }
    /*
     * Méthode qui gère la modification d'un CV
     * @return : la page d'édition si le CV à modifier appartient à l'utilisateur
     * @return : la page de visualisation si le CV n'appartient pas à l'utilsateur
     */
    public String updateCv() {
    	CurriculumVitae  cv = tm.updateCv(user, theCv);
    	if(cv.equals(null)) {
    		FacesContext ct = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(" cv could not be saved");
            ct.addMessage("test:enregistrement", msg);
            ct.validationFailed();
            return "editCv";
    	}
	    return "showCv";
    }
    /*
     * Métode pour supprimer une personne
     * @return : page de connexion si l'utilsateur non identifier
     * @return : page de visualisation si la personne n'est pas sur sa liste de personne créer
     * @return : la lise de personne après suppression
     */
    public String removePerson(Long id) {
    	if (testConnect == false) {
            FacesContext ct = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage("You must be connected before delete person !!!");
            ct.addMessage("test:connexion", msg);
            ct.validationFailed();
            return "login";
        }
    	// vérification de l'id de celui qui est connecté à l'id de admin
    	
    	if(!personAdmin.getId().equals(idConnected)) {
    		FacesContext ct = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage("You couldn't remove this person !!!");
            ct.addMessage("test:connexion", msg);
            ct.validationFailed();
            return "showPerson";
    		
    	}
    	if(tm.deletePersonne(user, accee.findPerson(id))==null) {
    		FacesContext ct = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage("person has been removed !!!");
            ct.addMessage("test:deleted", msg);  
            //tm.em.getTransaction().commit();
            return "listOfYourPerson";
        
    	}
    	FacesContext ct = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage("person not be removed !!!");
        ct.addMessage("test:delete", msg);
        ct.validationFailed();
        return "showPerson";
    	
    }
    /*
     * Méthode de suppression de CV
     * @return : page de connexion si l'utisateur non connecté
     * @return : page de visualisation de CVs si c'est pas le CV de l'utilisateur
     * @return : page de visualisation après suppression
     */
    public String removeCv(Long id) {
    	if (testConnect == false) {
            FacesContext ct = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage("You must be connected before delete CV !!!");
            ct.addMessage("test:connexion", msg);
            ct.validationFailed();
            return "login";
        }
    	
       Person p  = accee.findPerson(idConnected);
       if(p.getCv()!=null) {
    	   if(p.getCv().getId()!=id){
    	   		  FacesContext ct = FacesContext.getCurrentInstance();
    	            FacesMessage msg = new FacesMessage("You could not remove: it's not your cv !!!");
    	            ct.addMessage("test:modify", msg);
    	            ct.validationFailed();
    	            return "showCv";
    	   	   }
    	   if(tm.deleteCV(user, accee.findCV(id))!=null) {
	   			FacesContext ct = FacesContext.getCurrentInstance();
	   	        FacesMessage msg = new FacesMessage("cv not be delected !!!");
	   	        ct.addMessage("test:delete", msg);
	   	        ct.validationFailed();
	   	        return "showCv?faces-redirect=true";
   			}
    	   return "showCv?faces-redirect=true";
       }
       FacesContext ct = FacesContext.getCurrentInstance();
	   FacesMessage msg = new FacesMessage("you don't have cv to delete --> create your cv first");
	   ct.addMessage("test:delete", msg);
	   ct.validationFailed();
	   return "showCv?faces-redirect=true";
	  
    	
    }
    /*
     * Méthode qui permet visualiser la liste des personnes créer par la personne avec id en paramêtre
     * @param : id de la personne à qui on souhaiterai visualiser les personnes qu'il a crée
     * @return : page qui contient la liste des personnes créer par la personne
     */
    public  String showCooptationList(Long id) {
    	personAdmin = accee.findPerson(id);
    	return "listOfYourPerson"; 
    }
    
    
   
    


}