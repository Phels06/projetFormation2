package formation.sopra.projetFormation.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

import formation.sopra.projetFormation.entity.view.Views;

@Entity
@Table(name = "person_ad")
@NamedQueries({
		@NamedQuery(query = "select po from Postuler po left join fetch po.id pa left join fetch pa.personne pe left join fetch pa.annonce an", name = "Postuler.findAll") })
public class Postuler {
	@JsonView(Views.Common.class)
	@EmbeddedId
	private PostulerKey id;
	@Version
	private int version;

	public Postuler() {

	}

	public Postuler(PostulerKey id) {
		this.id = id;

	}

	public PostulerKey getId() {
		return id;
	}

	public void setId(PostulerKey id) {
		this.id = id;
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
		Postuler other = (Postuler) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
