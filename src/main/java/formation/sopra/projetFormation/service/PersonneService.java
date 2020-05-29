package formation.sopra.projetFormation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.sopra.projetFormation.entity.Personne;
import formation.sopra.projetFormation.repository.PersonneRepository;

@Service
public class PersonneService {

	private String regex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

	@Autowired
	private PersonneRepository personneRepository;


	public boolean ajout(Personne personne) {
		Boolean succes = true;
		char caractereString;
		Boolean majusculeFlag = false;
		Boolean minusculeFlag = false;
		Boolean numberFlag = false;
		if (personne.getAdresse().getNumero() == null) {
			succes = false;
		}
		if (personne.getAdresse().getRue().isEmpty()) {
			succes = false;
		}
		if (personne.getAdresse().getVille().isEmpty()) {
			succes = false;
		}
		if (personne.getAdresse().getCodePostal().isEmpty()) {
			succes = false;
		}
		if (personne.getCivilite() == null) {
			succes = false;
		}
		if (personne.getPrenom().isEmpty()) {
			succes = false;
		}
		if (personne.getNom().isEmpty()) {
			succes = false;
		}
		if (personne.getInscription().getMotDePasse().isEmpty()) {
			succes = false;
		}
		// au moins 1 minuscule, 1 majuscule1 1 chiffre
		if (succes == true) {
			for (int i = 0; i < personne.getInscription().getMotDePasse().length(); i++) {
				caractereString = personne.getInscription().getMotDePasse().charAt(i);
				if (Character.isDigit(caractereString)) {
					numberFlag = true;
				} else if (Character.isUpperCase(caractereString)) {
					majusculeFlag = true;
				} else if (Character.isLowerCase(caractereString)) {
					minusculeFlag = true;
				}
			}
			if (numberFlag && majusculeFlag && minusculeFlag) {
				succes = true;
			} else {
				succes = false;
			}
		}
		if (personne.getInscription().getMail().isEmpty()) {
			succes = false;
		}
		// test de l'email avec caract�re sp�cial, utilisation d'une regex
		if (personne.getInscription().getMail().matches(regex) == true && succes) {
			succes = true;
		} else {
			succes = false;
		}
		if (succes == true) {
			personneRepository.save(personne);
		}
		return succes;
	}

	
	
	public Personne miseAjour(Personne personne) {
		char caractereString;
		Boolean majusculeFlag = false;
		Boolean minusculeFlag = false;
		Boolean numberFlag = false;
		Optional<Personne> opt = personneRepository.findById(personne.getId());
		if (opt.isPresent()) {
			Personne personneEnBase = opt.get();
			if (personne.getInscription().getMotDePasse() != null) {
				for (int i = 0; i < personne.getInscription().getMotDePasse().length(); i++) {
					caractereString = personne.getInscription().getMotDePasse().charAt(i);
					if (Character.isDigit(caractereString)) {
						numberFlag = true;
					} else if (Character.isUpperCase(caractereString)) {
						majusculeFlag = true;
					} else if (Character.isLowerCase(caractereString)) {
						minusculeFlag = true;
					}
				}
				if (numberFlag && majusculeFlag && minusculeFlag) {
					personneEnBase.getInscription().setMotDePasse((personne.getInscription().getMotDePasse()));
				} else {
					throw new IllegalArgumentException();
				}
			}
			if (personne.getInscription().getMail() != null) {
				if (personne.getInscription().getMail().matches(regex) == true) {
					personneEnBase.getInscription().setMail((personne.getInscription().getMail()));
				} else {
					personne = personneEnBase;
					throw new IllegalArgumentException();
				}
			}
			if (personne.getAdresse().getNumero() != null) {
				personneEnBase.getAdresse().setNumero((personne.getAdresse().getNumero()));
			}
			if (personne.getAdresse().getRue() != null) {
				personneEnBase.getAdresse().setRue((personne.getAdresse().getRue()));
			}
			if (personne.getAdresse().getVille() != null) {
				personneEnBase.getAdresse().setVille((personne.getAdresse().getVille()));
			}
			if (personne.getAdresse().getCodePostal() != null) {
				personneEnBase.getAdresse().setCodePostal((personne.getAdresse().getCodePostal()));
			}
			if (personne.getPrenom() != null) {
				personneEnBase.setPrenom(personne.getPrenom());
			}
			if (personne.getNom() != null) {
				personneEnBase.setNom(personne.getNom());
			}
			if (personne.getNom() != null) {
				personneEnBase.setNom(personne.getNom());
			}
			if (personne.getCivilite().getLabel() != null) {
				personneEnBase.setCivilite(personne.getCivilite());
			}
			this.ajout(personneEnBase);
			return personneEnBase;
		} else {
			return null;
		}
	}


	public Personne recherche(Integer id) {
		Optional<Personne> opt = personneRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new IllegalArgumentException();
	}



}
