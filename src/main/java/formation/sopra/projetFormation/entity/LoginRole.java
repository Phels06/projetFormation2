package formation.sopra.projetFormation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import formation.sopra.projetFormation.entity.view.Views;

@Entity
@Table(name = "login_role")
@SequenceGenerator(name = "seqLoginRole", sequenceName = "seq_login_role", initialValue = 50, allocationSize = 1)
public class LoginRole {
	@JsonView(Views.Common.class)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqLoginRole")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "mail_person", foreignKey = @ForeignKey(name = "login_role_mail_person_fk"))
	private Personne personne;
	
	@JsonView(Views.Common.class)
	@Enumerated(EnumType.STRING)
	@Column(name = "role", length = 30)
	private Role role;

	public LoginRole() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
		LoginRole other = (LoginRole) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
