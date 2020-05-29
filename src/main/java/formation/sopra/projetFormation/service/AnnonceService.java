package formation.sopra.projetFormation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.sopra.projetFormation.entity.Annonce;
import formation.sopra.projetFormation.entity.Note;
import formation.sopra.projetFormation.repository.AnnonceRepository;

@Service
public class AnnonceService {

	@Autowired
	private AnnonceRepository annonceRepository;

	public boolean ajout(Annonce annonce) {
		boolean succes = true;
//		if (annonce.getDateAnnonce() == null) {
//			annonce.setDateAnnonce(new java.util.Date());
//		}
		if (annonce.getNote() == null) {
			annonce.setNote(Note.N5);
		}
		if (annonce.getDateAnnonce() == null || annonce.getMaitre() == null || annonce.getChiens().isEmpty()) {
			succes = false;
		}
		if (succes) {
			annonceRepository.save(annonce);
		}
		return succes;
	}

	public Annonce miseAjour(Annonce annonce) {
		Optional<Annonce> opt = annonceRepository.findById(annonce.getId());
		if (opt.isPresent()) {
			Annonce annonceEnBase = opt.get();
			if (annonce.getDateAnnonce() != null) {
				annonceEnBase.setDateAnnonce(annonce.getDateAnnonce());
			}
			if (annonce.getNote() != null) {
				annonceEnBase.setNote(annonce.getNote());
			}
			if (annonce.getMaitre() != null) {
				annonceEnBase.setMaitre(annonce.getMaitre());
			}
			if (annonce.getPromeneur() != null) {
				annonceEnBase.setPromeneur(annonce.getPromeneur());
			}
			if (annonce.getChiens() != null) {
				annonceEnBase.setChiens(annonce.getChiens());
			}
			if (annonce.getPostulers() != null) {
				annonceEnBase.setPostulers(annonce.getPostulers());
			}
			annonceRepository.save(annonceEnBase);
			return annonceEnBase;
		} else {
			// annonceRepository.save(annonce);// on insert
			return null;
		}
		// throw new NoAnnonceFoundException();

	}

	public Annonce recherche(Integer id) {
		Optional<Annonce> opt = annonceRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new IllegalArgumentException();
	}
	
	public List<Annonce> rechercheTous() {
		return annonceRepository.findAll();
	}
	
	public boolean supprimerParId(Integer id) {
		boolean succes = true;
		annonceRepository.deleteById(id);
		return succes;
	}

}
