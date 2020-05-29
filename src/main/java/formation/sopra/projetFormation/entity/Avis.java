package formation.sopra.projetFormation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

import formation.sopra.projetFormation.entity.view.Views;

@Entity
@Table(name = "opinion")
@SequenceGenerator(name = "seqAvis", sequenceName = "seq_opinion", initialValue = 100, allocationSize = 1)
public class Avis {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAvis")
	@Column(name = "id_opinion")
	@JsonView(Views.Common.class)
	private Integer id;
	@Column(name = "opinion")
	@JsonView(Views.Common.class)
	private String avis;
	@ManyToOne
	@JoinColumn(name = "id_person", foreignKey = @ForeignKey(name = "opinion_person_fk"))
	private Personne personne;
	@Version
	private int version;

	public Avis() {
	}

	public Avis(Integer id, String avis) {
		this.id = id;
		this.avis = avis;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAvisMaitre() {
		return avis;
	}

	public void setAvisMaitre(String avis) {
		this.avis = avis;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
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
		result = prime * result + ((avis == null) ? 0 : avis.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((personne == null) ? 0 : personne.hashCode());
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
		Avis other = (Avis) obj;
		if (avis == null) {
			if (other.avis != null)
				return false;
		} else if (!avis.equals(other.avis))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (personne == null) {
			if (other.personne != null)
				return false;
		} else if (!personne.equals(other.personne))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}
