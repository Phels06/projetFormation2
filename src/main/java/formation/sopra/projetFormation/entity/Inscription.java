package formation.sopra.projetFormation.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Embeddable
public class Inscription {
	//@Temporal(TemporalType.DATE) ??
	private LocalDate dateInscription;
	@Email
	private String mail;
	@Pattern( regexp="^([+-])?[0-9]+([,.][0-9]{1,2})?", 
			 message="mot de passe invalide. Il doit suivre ######,## ou #####.##")
	private String motDePasse;
	private boolean enable;
	@OneToMany(mappedBy = "personne")
	private Set<LoginRole> roles;

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

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Set<LoginRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<LoginRole> roles) {
		this.roles = roles;
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
