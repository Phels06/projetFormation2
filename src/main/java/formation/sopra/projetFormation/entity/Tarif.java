package formation.sopra.projetFormation.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Tarif {
	@Column(name = "number_of_dogs")
	private Integer nbChiens;
	@Column(name = "number_of_hours")
	private Integer nbHeures;
	@Column(name = "price_in_respect_to_dog_in_respect_to_hour")
	private Integer prixIRTchienIRTheure;
	@Column(name = "tax")
	private Integer taxe;
	@Column(name = "total_price")
	private Integer prixTotal;

	public Tarif() {
	}

	public Tarif(Integer nbChiens, Integer nbHeures, Integer prixIRTchienIRTheure, Integer taxe, Integer prixTotal) {
		this.nbChiens = nbChiens;
		this.nbHeures = nbHeures;
		this.prixIRTchienIRTheure = prixIRTchienIRTheure;
		this.taxe = taxe;
		this.prixTotal = prixTotal;
	}

	public Integer getNbChiens() {
		return nbChiens;
	}

	public void setNbChiens(Integer nbChiens) {
		this.nbChiens = nbChiens;
	}

	public Integer getNbHeures() {
		return nbHeures;
	}

	public void setNbHeures(Integer nbHeures) {
		this.nbHeures = nbHeures;
	}

	public Integer getPrixIRTchienIRTheure() {
		return prixIRTchienIRTheure;
	}

	public void setPrixIRTchienIRTheure(Integer prixIRTchienIRTheure) {
		this.prixIRTchienIRTheure = prixIRTchienIRTheure;
	}

	public Integer getTaxe() {
		return taxe;
	}

	public void setTaxe(Integer taxe) {
		this.taxe = taxe;
	}

	public Integer getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Integer prixTotal) {
		this.prixTotal = prixTotal;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((nbChiens == null) ? 0 : nbChiens.hashCode());
//		result = prime * result + ((nbHeures == null) ? 0 : nbHeures.hashCode());
//		result = prime * result + ((prixIRTchienIRTheure == null) ? 0 : prixIRTchienIRTheure.hashCode());
//		result = prime * result + ((prixTotal == null) ? 0 : prixTotal.hashCode());
//		result = prime * result + ((taxe == null) ? 0 : taxe.hashCode());
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
//		Tarif other = (Tarif) obj;
//		if (nbChiens == null) {
//			if (other.nbChiens != null)
//				return false;
//		} else if (!nbChiens.equals(other.nbChiens))
//			return false;
//		if (nbHeures == null) {
//			if (other.nbHeures != null)
//				return false;
//		} else if (!nbHeures.equals(other.nbHeures))
//			return false;
//		if (prixIRTchienIRTheure == null) {
//			if (other.prixIRTchienIRTheure != null)
//				return false;
//		} else if (!prixIRTchienIRTheure.equals(other.prixIRTchienIRTheure))
//			return false;
//		if (prixTotal == null) {
//			if (other.prixTotal != null)
//				return false;
//		} else if (!prixTotal.equals(other.prixTotal))
//			return false;
//		if (taxe == null) {
//			if (other.taxe != null)
//				return false;
//		} else if (!taxe.equals(other.taxe))
//			return false;
//		return true;
//	}

}
