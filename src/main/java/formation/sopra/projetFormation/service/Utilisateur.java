package formation.sopra.projetFormation.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import formation.sopra.projetFormation.entity.LoginRole;
import formation.sopra.projetFormation.entity.Personne;

public class Utilisateur implements UserDetails {

	private Personne login;

	public Utilisateur(Personne login) {
		this.login = login;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		for (LoginRole loginRole : login.getInscription().getRoles()) {
			authorities.add(new SimpleGrantedAuthority(loginRole.getRole().toString()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return login.getInscription().getMotDePasse();
	}

	@Override
	public String getUsername() {
		return login.getInscription().getMail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return login.getInscription().isEnable();
	}

}
