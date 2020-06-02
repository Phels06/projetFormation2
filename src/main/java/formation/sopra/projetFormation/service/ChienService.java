package formation.sopra.projetFormation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import formation.sopra.projetFormation.entity.Chien;
import formation.sopra.projetFormation.repository.ChienRepository;

@Service
public class ChienService {

	@Autowired
	private ChienRepository chienRepository;

	public boolean ajout(Chien chien) {
		boolean success = true;
		if (chien.getPhoto().isEmpty()) {
			chien.setPhoto("photo non defini");
		}
		if (chien.getRace().isEmpty()) {
			chien.setRace("race non defini");
		}
		if (chien.getSurnom().isEmpty() || chien.getSexeChien() == null || chien.getAge() == null
				|| chien.getPoids() == null || chien.getAnnonce() == null || chien.getPersonne() == null) {
			success = false;
		}
		if (success) {
			chienRepository.save(chien);
		}
		return success;
	}

	
	public Chien miseAjour(Chien chien) {
		Optional<Chien> opt = chienRepository.findById(chien.getId());
		if (opt.isPresent()) {
			Chien chienEnBase = opt.get();
			if (chien.getSurnom() != null) {
				chienEnBase.setSurnom(chien.getSurnom());
			}
			if (chien.getSexeChien() != null) {
				chienEnBase.setSexeChien(chien.getSexeChien());
			}
			if (chien.getAge() != null) {
				chienEnBase.setAge(chien.getAge());
			}
			if (chien.getPhoto() != null) {
				chienEnBase.setPhoto(chien.getPhoto());
			}
			if (chien.getPoids() != null) {
				chienEnBase.setPoids(chien.getPoids());
			}
			if (chien.getRace() != null) {
				chienEnBase.setRace(chien.getRace());
			}
			if (chien.getAnnonce() != null) {
				chienEnBase.setAnnonce(chien.getAnnonce());
			}
			if (chien.getPersonne() != null) {
				chienEnBase.setPersonne(chien.getPersonne());
			}
			chienRepository.save(chienEnBase);
			return chienEnBase;
		} else {
			return null;
		}
	}

	
	public Chien recherche(Integer id) {
		Optional<Chien> opt = chienRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new IllegalArgumentException();
	}
	
	
	public List<Chien> rechercheAll() {
		return chienRepository.findAll();
	}
	
	
}
