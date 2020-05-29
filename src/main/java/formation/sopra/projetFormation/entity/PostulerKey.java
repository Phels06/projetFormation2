package formation.sopra.projetFormation.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

import formation.sopra.projetFormation.entity.view.Views;

@Embeddable
public class PostulerKey implements Serializable {
	@JsonView(Views.Common.class)
	@ManyToOne
	@JoinColumn(name = "id_person_ad_person", foreignKey = @ForeignKey(name = "person_ad_person_fk"))
	private Personne personne;
	@JsonView(Views.Common.class)
	@ManyToOne
	@JoinColumn(name = "id_person_ad_ad", foreignKey = @ForeignKey(name = "person_ad_ad_fk"))
	private Annonce annonce;

	public PostulerKey() {

	}

	public PostulerKey(Personne personne, Annonce annonce) {
		this.personne = personne;
		this.annonce = annonce;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Annonce getAnnonce() {
		return annonce;
	}

	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((annonce == null) ? 0 : annonce.hashCode());
		result = prime * result + ((personne == null) ? 0 : personne.hashCode());
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
		PostulerKey other = (PostulerKey) obj;
		if (annonce == null) {
			if (other.annonce != null)
				return false;
		} else if (!annonce.equals(other.annonce))
			return false;
		if (personne == null) {
			if (other.personne != null)
				return false;
		} else if (!personne.equals(other.personne))
			return false;
		return true;
	}

}
