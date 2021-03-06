package formation.sopra.projetFormation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import formation.sopra.projetFormation.entity.Personne;
import formation.sopra.projetFormation.repository.PersonneRepository;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private PersonneRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		Optional<Personne> opt = loginRepository.findByMailWithRoles(username);
		if (!opt.isPresent()) {
			throw new UsernameNotFoundException("utlisateur inconnu");
		}
		Utilisateur user = new Utilisateur(opt.get());
		return user;
	}

}
