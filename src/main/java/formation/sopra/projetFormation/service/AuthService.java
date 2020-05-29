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
		Optional<Personne> opt = loginRepository.findByIdWithRoles(username);
		if (!opt.isPresent()) {
			throw new UsernameNotFoundException("utlisateur inconnu");
		}
		Personne user = new Personne(opt.get());
		return user;
	}

}
