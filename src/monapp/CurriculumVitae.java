/*
 * Bean pour créer une entité cv
 */

package monapp;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "cv")
public class CurriculumVitae implements Serializable {

    private static final long serialVersionUID = 1L;
    /*
     * Id du cv
     */
    @Id @GeneratedValue
    private Long id;
    /*
     * titre du cv
     */
    @Column
    private String title;
    /*
     * anneé en cours
     */
    @Column
    private Integer year;
    /*
     * expériences professionnelle
     */
    @Lob
    @Column
    private String experiencePro;
    /*
     * formation
     */
    @Lob
    @Column
    private String formation;
    /*
     * projets
     */
    @Lob
    @Column
    private String projects;
    /*
     * autres expériences
     */
    @Lob
    @Column
    private String other;
    /*
     * description du cv
     */
    @Lob
    @Column
    private String description;
    /*
     * adresse du site web 
     */
	@Column
    private String addressWeb;
    
    /*
     * constructeur
     */
    public CurriculumVitae() {
		super();
		// TODO Auto-generated constructor stub
	}
    /*
     * Mapping OneToOne : désignant qu'une personne ne peut posséder qu'un seul cv
     */
	@OneToOne(optional = true, cascade = { CascadeType.PERSIST},
			fetch = FetchType.LAZY)
    @JoinColumn(name = "person", nullable = true)
    private Person person;
    
    public void setPerson(Person person) {
    	this.person = person;
	}
    
	public Long getId() {
		return id;
	}

	public Person getPerson() {
		return person;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getExperiencePro() {
		return experiencePro;
	}

	public void setExperiencePro(String experiencePro) {
		this.experiencePro = experiencePro;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public String getProjects() {
		return projects;
	}

	public void setProjects(String projects) {
		this.projects = projects;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddressWeb() {
		return addressWeb;
	}

	public void setAddressWeb(String addressWeb) {
		this.addressWeb = addressWeb;
	}
}