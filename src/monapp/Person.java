/*
 * Bean pour créer une entité personne
 */

package monapp;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /*
     * Id de la personne
     */
    @Id @GeneratedValue
    private Long id;
    
    /*
     * controlleur 
     */
    public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
    /*
     * nom de de la personne
     */
	@Column
    private String name;
    
	/*
	 * prénom de la personne
	 */
    @Column
    private String firstName;

	/*
	 * email de la personne
	 */
	@Column
    private  String email;
    /*
     * site web de la personne
     */
    @Column
    private String webSite;
    /*
     * date de naissance de la personne
     */
    @Column
    private String birthDay;
    /*
     * mot de passe de la personne
     */
    @Column
    private String password;
    /*
     * Mapping OneTone: desigant qu'une persone ne peut céer q"un seul cv
     */
    @OneToOne(orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
		      fetch = FetchType.EAGER, mappedBy = "person")
			  private CurriculumVitae cv;
    /*
     * Mapping OneToMany : désigant qu'une personne peux créer plusieurs personnes
     */
    @OneToMany( orphanRemoval = true,  cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE},
    		fetch = FetchType.EAGER, mappedBy = "owner")
  		  private Set<Person> persons;  
    /*
     * Mapping ManyToOne : désigant q'une personne peux créer plusieurs personnes
     */
    @ManyToOne(optional = true, cascade = { CascadeType.PERSIST, CascadeType.REFRESH },
    		fetch = FetchType.LAZY)
	   @JoinColumn(name = "owner", nullable = true)
    private Person owner;
    
    
    public void setPerson(Person person) {
    	this.owner = person;
    }
    
    public void addPerson(Person person) {
    	if (persons == null) {
	    	  persons = new HashSet<>();
	      }
	      persons.add(person);
	      person.setPerson(this);
	}    
    

    public void setCv(CurriculumVitae cv) {
    	 if (cv == null) {
             if (this.cv != null) {
                 this.cv.setPerson(null);
             }
         }
         else {
             cv.setPerson(this);
         }
         this.cv = cv;
	}
    
	public Set<Person> getPersons() {
		return persons;
	}
	/*public Person getPerson(Long id) {
		return persons.
	}*/

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	public CurriculumVitae getCv() {
		return cv;
	}

	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
/* à faire : chaque personne peut renseigner un cv*/
}