//package formation.sopra.projetFormation.service;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import formation.sopra.formationSpringBoot.entity.Login;
//import formation.sopra.formationSpringBoot.entity.LoginRole;
//
//public class Utilisateur implements UserDetails {
//
//	private Login login;
//
//	public Utilisateur(Login login) {
//		this.login = login;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//		for (LoginRole loginRole : login.getRoles()) {
//			authorities.add(new SimpleGrantedAuthority(loginRole.getRole().toString()));
//		}
//		return authorities;
//	}
//
//	@Override
//	public String getPassword() {
//		return login.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		return login.getLogin();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return login.isEnable();
//	}
//
//}
