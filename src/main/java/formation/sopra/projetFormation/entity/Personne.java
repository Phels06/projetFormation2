package formation.sopra.projetFormation.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

import formation.sopra.projetFormation.entity.view.Views;

@Entity
@Table(name = "person")
@SequenceGenerator(name = "seqPersonne", sequenceName = "seq_person", initialValue = 100, allocationSize = 1)
public class Personne {
	@JsonView(Views.Common.class)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPersonne")
	@Column(name = "id_person")
	private Integer id;
	@JsonView(Views.Common.class)
	@Column(name = "first_name", length = 150, nullable = false)
	@NotEmpty
	private String prenom;
	@JsonView(Views.Common.class)
	@Column(name = "last_name", length = 150, nullable = false)
	private String nom;
	@JsonView(Views.Common.class)
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "dateInscription", column = @Column(name = "inscription_date_person")),
			@AttributeOverride(name = "mail", column = @Column(name = "mail_person", nullable = false)),
			@AttributeOverride(name = "motDePasse", column = @Column(name = "password_person", length = 250, nullable = false)),
			@AttributeOverride(name = "enable", column = @Column(name = "enable_person", nullable = false)) })
	private Inscription inscription;
	@JsonView(Views.Common.class)
	@Column(name = "title", length = 4)
	@Enumerated(EnumType.STRING)
	private Civilite civilite;
	@JsonView(Views.Common.class)
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "numero", column = @Column(name = "number_person", nullable = false)),
			@AttributeOverride(name = "rue", column = @Column(name = "street_person", length = 200, nullable = false)),
			@AttributeOverride(name = "codePostal", column = @Column(name = "zip_code_person", length = 20, nullable = false)),
			@AttributeOverride(name = "ville", column = @Column(name = "city_person", length = 150, nullable = false)) })
	private Adresse adresse;
	@OneToMany(mappedBy = "personne")
	private Set<Chien> chiens;
	@OneToMany(mappedBy = "personne")
	private List<Avis> avis;
	@OneToMany(mappedBy = "id.personne")
	private Set<Postuler> postulers;
	@OneToMany(mappedBy = "maitre")
	private Set<Annonce> annoncesPoste = new HashSet<>();
	@OneToMany(mappedBy = "promeneur")
	private Set<Annonce> annoncesRepondu = new HashSet<>();
	@Version
	private int version;

	public Personne() {
	}

	public Personne(Integer id, String prenom, String nom) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
	}

	public Personne(String prenom, String nom, Inscription inscription) {
		this.prenom = prenom;
		this.nom = nom;
		this.inscription = inscription;
	}

	public Personne(String prenom, String nom, Inscription inscription, Civilite civilite) {
		this.prenom = prenom;
		this.nom = nom;
		this.inscription = inscription;
		this.civilite = civilite;
	}

	public Personne(String prenom, String nom, Inscription inscription, Adresse adresse) {
		this.prenom = prenom;
		this.nom = nom;
		this.inscription = inscription;
		this.adresse = adresse;
	}

	public Personne(String prenom, String nom, Inscription inscription, Civilite civilite, Adresse adresse) {
		this.prenom = prenom;
		this.nom = nom;
		this.inscription = inscription;
		this.civilite = civilite;
		this.adresse = adresse;
	}

	public Personne(Integer id, String prenom, String nom, Inscription inscription, Civilite civilite,
			Adresse adresse) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.inscription = inscription;
		this.civilite = civilite;
		this.adresse = adresse;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Inscription getInscription() {
		return inscription;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Set<Chien> getChiens() {
		return chiens;
	}

	public void setChiens(Set<Chien> chiens) {
		this.chiens = chiens;
	}

	public List<Avis> getAvis() {
		return avis;
	}

	public void setAvis(List<Avis> avis) {
		this.avis = avis;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Set<Postuler> getPostulers() {
		return postulers;
	}

	public void setPostulers(Set<Postuler> postulers) {
		this.postulers = postulers;
	}

	public Set<Annonce> getAnnoncesPoste() {
		return annoncesPoste;
	}

	public void setAnnoncesPoste(Set<Annonce> annoncesPoste) {
		this.annoncesPoste = annoncesPoste;
	}

	public Set<Annonce> getAnnoncesRepondu() {
		return annoncesRepondu;
	}

	public void setAnnoncesRepondu(Set<Annonce> annoncesRepondu) {
		this.annoncesRepondu = annoncesRepondu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
