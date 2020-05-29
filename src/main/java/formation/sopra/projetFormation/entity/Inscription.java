package formation.sopra.projetFormation.entity;

import java.time.LocalDate;

import javax.persistence.Embeddable;

@Embeddable
public class Inscription {
	//@Temporal(TemporalType.DATE) ??
	private LocalDate dateInscription;
	private String mail;
	private String motDePasse;

	public Inscription() {
	}

	public Inscription(LocalDate dateInscription, String mail, String motDePasse) {
		this.dateInscription = dateInscription;
		this.mail = mail;
		this.motDePasse = motDePasse;
	}

	public Inscription(String mail, String motDePasse) {
		this.dateInscription = LocalDate.now();
		this.mail = mail;
		this.motDePasse = motDePasse;
	}

	public LocalDate getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(LocalDate dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMotdePasse() {
		return motDePasse;
	}

	public void setMotdePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((motDePasse == null) ? 0 : motDePasse.hashCode());
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
		Inscription other = (Inscription) obj;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (motDePasse == null) {
			if (other.motDePasse != null)
				return false;
		} else if (!motDePasse.equals(other.motDePasse))
			return false;
		return true;
	}

}
