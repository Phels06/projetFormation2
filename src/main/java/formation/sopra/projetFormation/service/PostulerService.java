package formation.sopra.projetFormation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.sopra.projetFormation.entity.Postuler;
import formation.sopra.projetFormation.entity.PostulerKey;
import formation.sopra.projetFormation.repository.PostulerRepository;

@Service
public class PostulerService {

	@Autowired
	private PostulerRepository postulerRepository;

	public boolean ajout(Postuler postuler) {
		boolean succes = true;
		if (postuler.getId().getPersonne() != null && postuler.getId().getAnnonce() != null) {
			postulerRepository.save(postuler);
		} else {
			succes = false;
		}
		return succes;
	}

	public Postuler recherche(PostulerKey id) {
		Optional<Postuler> opt = postulerRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new IllegalArgumentException();
	}

}
