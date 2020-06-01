package formation.sopra.projetFormation.restController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import formation.sopra.projetFormation.entity.LoginRole;
import formation.sopra.projetFormation.entity.Personne;
import formation.sopra.projetFormation.entity.Role;
import formation.sopra.projetFormation.entity.view.Views;
import formation.sopra.projetFormation.repository.LoginRoleRepository;
import formation.sopra.projetFormation.repository.PersonneRepository;
import formation.sopra.projetFormation.service.PersonneService;

@RestController
@RequestMapping("/rest/")
@CrossOrigin(origins = "*")
public class PersonneRestController {

	@Autowired
	PersonneService personneService;

	@Autowired
	PersonneRepository personneRepository;

	@Autowired
	LoginRoleRepository loginRoleRepository;
	
	@Autowired
	private PasswordEncoder PasswordEncoder;

	// lister les personnes qui sont inscrites
	@JsonView(Views.Common.class)
	@GetMapping({ "personne", "personne/" })
	public ResponseEntity<List<Personne>> finAll() {
		return new ResponseEntity<List<Personne>>(personneRepository.findAll(), HttpStatus.OK);
	}

	// inscrir une personne
	@PostMapping({ "inscription", "inscription/" })
	public ResponseEntity<Void> addPersonne(@Valid @RequestBody Personne personne, BindingResult br,
			UriComponentsBuilder uCB) {
		LocalDate dateInscription = LocalDate.now();
		char caractereString;
		Boolean majusculeFlag = false;
		Boolean minusculeFlag = false;
		Boolean numberFlag = false;
		String regex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		if (br.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		//on vérifie si l'email est unique
		Optional<Personne> optional=personneRepository.findByMail(personne.getInscription().getMail());
		if(optional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		// au moins 1 minuscule, 1 majuscule1 1 chiffre dans le mot de passe
		if (!br.hasErrors()) {
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
			if (!numberFlag || !majusculeFlag || !minusculeFlag) {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		}
		// test de l'email avec caractere special, utilisation d'une regex
		if (!br.hasErrors() && !personne.getInscription().getMail().matches(regex)) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		if (!br.hasErrors() && personne.getInscription().getMotDePasse().length() < 8) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		personne.getInscription().setDateInscription(dateInscription);
		personne.getInscription().setEnable(true);
		personne.getInscription().setMotDePasse(PasswordEncoder.encode(personne.getInscription().getMotDePasse()));
		personneService.ajout(personne);
		LoginRole role=new LoginRole();
		role.setRole(Role.ROLE_USER);
		role.setPersonne(personne);
		loginRoleRepository.save(role);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	
	// rechercher une personne selon son id
	@GetMapping("personne/{id}")
	public ResponseEntity<Personne> findById(@PathVariable("id") Integer id) {
		Optional<Personne> opt = personneRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Personne>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	
	// supprimer une personne selon son id
	@DeleteMapping("personne/delete/{id}")
	public ResponseEntity<Void> deletePersonne(@PathVariable("id") Integer id) {
		Optional<Personne> opt = personneRepository.findById(id);
		if (opt.isPresent()) {
			loginRoleRepository.deleteById(loginRoleRepository.findByIdPersonne(id));
			personneRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	
	// mise à jour d'une personne selon son id
	@PutMapping("personne/maj/{id}")
	public ResponseEntity<Void> updatePersonne(@Valid @RequestBody Personne personne, BindingResult br,
			@PathVariable("id") Integer id) {
		char caractereString;
		Boolean majusculeFlag = false;
		Boolean minusculeFlag = false;
		Boolean numberFlag = false;
		String regex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		if (br.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		Optional<Personne> opt = personneRepository.findById(id);
		Personne personneEnBase = opt.get();
		if (opt.isPresent()) {
			personneEnBase.setPrenom(personne.getPrenom());
			personneEnBase.setNom(personne.getNom());
			personneEnBase.setCivilite(personne.getCivilite());

			personneEnBase.getAdresse().setNumero(personne.getAdresse().getNumero());
			personneEnBase.getAdresse().setRue(personne.getAdresse().getRue());
			personneEnBase.getAdresse().setCodePostal(personne.getAdresse().getCodePostal());
			personneEnBase.getAdresse().setVille(personne.getAdresse().getVille());

			if (personne.getAdresse() != null) {
				personneEnBase.getAdresse().setCodePostal(personne.getAdresse().getCodePostal());
				personneEnBase.getAdresse().setNumero(personne.getAdresse().getNumero());
				personneEnBase.getAdresse().setRue(personne.getAdresse().getRue());
				personneEnBase.getAdresse().setVille(personne.getAdresse().getVille());
			}
			if (personne.getInscription() != null) {
				//email
				if (!personne.getInscription().getMail().matches(regex)) {
					return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
				}
				personneEnBase.getInscription().setMail(personne.getInscription().getMail());
				//taille du mot de passe
				if (!br.hasErrors() && personne.getInscription().getMotDePasse().length() < 8) {
					return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
				}
				// au moins 1 minuscule, 1 majuscule1 1 chiffre dans le mot de passe
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
				if (!numberFlag || !majusculeFlag || !minusculeFlag) {
					return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
				}
				personneEnBase.getInscription().setMotDePasse(PasswordEncoder.encode(personne.getInscription().getMotDePasse()));
			}

			personneEnBase = personneRepository.save(personneEnBase);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	
	// recherche d'un email déja existant
	@GetMapping("/personne/login/{mail}")
	public ResponseEntity<Boolean> loginDispo(@PathVariable("mail") String mail){
		Optional<Personne>opt=personneRepository.findByMail(mail);
		if(opt.isPresent()) {
			return new ResponseEntity<>(false,HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
}
