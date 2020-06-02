package formation.sopra.projetFormation.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonView;

import formation.sopra.projetFormation.entity.view.Views;



@Embeddable
public class Adresse {
	@JsonView(Views.Common.class)
	@Column(name = "number")
	private Integer numero;
	@JsonView(Views.Common.class)
	@Column(name = "street", length = 200)
	private String rue;
	@JsonView(Views.Common.class)
	@Column(name = "zip_code", length = 20)
	private String codePostal;
	@JsonView(Views.Common.class)
	@Column(name = "city", length = 150)
	private String ville;

	public Adresse() {
	}
	
	public Adresse(String ville) {
		this.ville = ville;
	}


	public Adresse(Integer numero, String rue, String codePostal, String ville) {
		super();
		this.numero = numero;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((codePostal == null) ? 0 : codePostal.hashCode());
//		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
//		result = prime * result + ((rue == null) ? 0 : rue.hashCode());
//		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Adresse other = (Adresse) obj;
//		if (codePostal == null) {
//			if (other.codePostal != null)
//				return false;
//		} else if (!codePostal.equals(other.codePostal))
//			return false;
//		if (numero == null) {
//			if (other.numero != null)
//				return false;
//		} else if (!numero.equals(other.numero))
//			return false;
//		if (rue == null) {
//			if (other.rue != null)
//				return false;
//		} else if (!rue.equals(other.rue))
//			return false;
//		if (ville == null) {
//			if (other.ville != null)
//				return false;
//		} else if (!ville.equals(other.ville))
//			return false;
//		return true;
//	}

}
