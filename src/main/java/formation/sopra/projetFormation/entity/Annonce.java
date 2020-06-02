package formation.sopra.projetFormation.entity;

import java.util.Date;
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
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

import formation.sopra.projetFormation.entity.view.Views;

@Entity
@Table(name = "ad")
@NamedQueries({
		@NamedQuery(query = "select a from Annonce a left join fetch a.maitre m left join fetch a.promeneur pr left join fetch a.chiens c left join fetch a.postulers po", name = "Annonce.findAll") })
@SequenceGenerator(name = "seqAnnonce", sequenceName = "seq_ad", initialValue = 100, allocationSize = 1)
public class Annonce {
	@JsonView(Views.Common.class)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAnnonce")
	@Column(name = "id_ad")
	private Integer id;

	@JsonView(Views.Common.class)
	@Column(name = "date_ad")
	@Temporal(TemporalType.DATE)
	@FutureOrPresent
	private Date dateAnnonce;

	@JsonView(Views.Common.class)
	@Enumerated(EnumType.STRING)
	private Note note;

	@JsonView(Views.Common.class)
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "nbChiens", column = @Column(name = "number_of_dogs")),
			@AttributeOverride(name = "nbHeures", column = @Column(name = "number_of_hours")),
			@AttributeOverride(name = "prixIRTchienIRTheure", column = @Column(name = "price_irt_dog_irt_hour")),
			@AttributeOverride(name = "taxe", column = @Column(name = "tax")),
			@AttributeOverride(name = "prixTotal", column = @Column(name = "total_price")) })
	private Tarif tarif;

	@OneToOne()
	@JoinColumn(name = "id_master", foreignKey = @ForeignKey(name = "ad_master_fk"))
	// @NotEmpty
	@JsonView(Views.CommonAnnonce.class)
	private Personne maitre;

	@OneToOne()
	@JsonView(Views.CommonAnnonce.class)
	@JoinColumn(name = "id_walker", foreignKey = @ForeignKey(name = "ad_walker_fk"))
	private Personne promeneur;
	@OneToMany(mappedBy = "annonce")
	private Set<Chien> chiens = new HashSet<>();
	@JsonView(Views.AnnonceByPersonne.class)
	@OneToMany(mappedBy = "id.annonce")
	private List<Postuler> postulers;
	@Version
	private int version;

	public Annonce() {

	}

	public Annonce(Date dateAnnonce, Note note) {
		this.dateAnnonce = dateAnnonce;
		this.note = note;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateAnnonce() {
		return dateAnnonce;
	}

	public void setDateAnnonce(Date dateAnnonce) {
		this.dateAnnonce = dateAnnonce;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public Tarif getTarif() {
		return tarif;
	}

	public void setTarif(Tarif tarif) {
		this.tarif = tarif;
	}

	public Personne getMaitre() {
		return maitre;
	}

	public void setMaitre(Personne maitre) {
		this.maitre = maitre;
	}

	public Personne getPromeneur() {
		return promeneur;
	}

	public void setPromeneur(Personne promeneur) {
		this.promeneur = promeneur;
	}

	public Set<Chien> getChiens() {
		return chiens;
	}

	public void setChiens(Set<Chien> chiens) {
		this.chiens = chiens;
	}

	public List<Postuler> getPostulers() {
		return postulers;
	}

	public void setPostulers(List<Postuler> postulers) {
		this.postulers = postulers;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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
		Annonce other = (Annonce) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
