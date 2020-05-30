package formation.sopra.projetFormation.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import formation.sopra.projetFormation.entity.Personne;
import formation.sopra.projetFormation.entity.view.Views;
import formation.sopra.projetFormation.repository.PersonneRepository;
import formation.sopra.projetFormation.service.PersonneService;


@RestController
@RequestMapping("/rest/personne")
@CrossOrigin(origins = "*")
public class PersonneRestController {

	@Autowired
	PersonneService personneService;
	
	@Autowired
	PersonneRepository personneRepository;
	
	
	//ok
	@JsonView(Views.Common.class)
	@GetMapping({ "", "/" })
	public ResponseEntity<List<Personne>> finAll() {
		return new ResponseEntity<List<Personne>>(personneRepository.findAll(), HttpStatus.OK);
	}
	
	//ok
	@PostMapping({ "", "/" })
	public ResponseEntity<Void> addPersonne(@RequestBody Personne personne, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		personneService.ajout(personne);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/{id}").buildAndExpand(personne.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	//ok
	@GetMapping("/{id}")
	public ResponseEntity<Personne> findById(@PathVariable("id") Integer id) {
		Optional<Personne> opt = personneRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Personne>(opt.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//ok
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deletePersonne(@PathVariable("id") Integer id) {
		Optional<Personne> opt = personneRepository.findById(id);
		if (opt.isPresent()) {
			personneRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Void> updatePersonne(@RequestBody Personne personne, BindingResult br,
			@PathVariable("id") Integer id) {
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
				personneEnBase.getInscription().setMail(personne.getInscription().getMail());
				personneEnBase.getInscription().setMotDePasse(personne.getInscription().getMotDePasse());
			}
			
			personneEnBase = personneRepository.save(personneEnBase);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
