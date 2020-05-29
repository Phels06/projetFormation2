package formation.sopra.projetFormation.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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


@Entity
@Table(name = "person")
@SequenceGenerator(name = "seqPersonne", sequenceName = "seq_person", initialValue = 100, allocationSize = 1)
public class Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPersonne")
	@Column(name = "id_person")
	private Integer id;
	@Column(name = "first_name", length = 150, nullable = false)
	@NotEmpty
	private String prenom;
	@Column(name = "last_name", length = 150, nullable = false)
	private String nom;
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "dateInscription", column = @Column(name = "inscription_date_person", nullable = false)),
			@AttributeOverride(name = "mail", column = @Column(name = "mail_person", nullable = false)),
			@AttributeOverride(name = "motDePasse", column = @Column(name = "password_person", length = 50, nullable = false)) })
	private Inscription inscription;
	@Column(name = "title", length = 4)
	@Enumerated(EnumType.STRING)
	private Civilite civilite;
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
		this.id=id;
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
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((annoncesPoste == null) ? 0 : annoncesPoste.hashCode());
		result = prime * result + ((annoncesRepondu == null) ? 0 : annoncesRepondu.hashCode());
		result = prime * result + ((avis == null) ? 0 : avis.hashCode());
		result = prime * result + ((civilite == null) ? 0 : civilite.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inscription == null) ? 0 : inscription.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((postulers == null) ? 0 : postulers.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + version;
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
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (annoncesPoste == null) {
			if (other.annoncesPoste != null)
				return false;
		} else if (!annoncesPoste.equals(other.annoncesPoste))
			return false;
		if (annoncesRepondu == null) {
			if (other.annoncesRepondu != null)
				return false;
		} else if (!annoncesRepondu.equals(other.annoncesRepondu))
			return false;
		if (avis == null) {
			if (other.avis != null)
				return false;
		} else if (!avis.equals(other.avis))
			return false;
		if (civilite != other.civilite)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inscription == null) {
			if (other.inscription != null)
				return false;
		} else if (!inscription.equals(other.inscription))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (postulers == null) {
			if (other.postulers != null)
				return false;
		} else if (!postulers.equals(other.postulers))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}
