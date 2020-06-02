package formation.sopra.projetFormation.restController;

import java.net.URI;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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



import formation.sopra.projetFormation.entity.Chien;
import formation.sopra.projetFormation.entity.view.Views;
import formation.sopra.projetFormation.repository.ChienRepository;


@RestController
@RequestMapping("/rest/chien")
@CrossOrigin(origins = "*")
public class ChienRestController {

	
	@Autowired
	private ChienRepository chienRepository;
	
	
	@GetMapping({ "", "/" })
	@JsonView(Views.ChienWithAnnonce.class)
	public ResponseEntity<List<Chien>> getAllChien() {
		return new ResponseEntity<>(chienRepository.findAll(), HttpStatus.OK);
	}
	
	@JsonView(Views.Common.class)
	@GetMapping("/only")
	public ResponseEntity<List<Chien>> findAllOnlyChien() {
		List<Chien> list = chienRepository.findAll();
		return new ResponseEntity<List<Chien>>(list, HttpStatus.OK);	
	}
	
	
	@PostMapping({ "", "/" })
	public ResponseEntity<Void> addChien(@Valid @RequestBody Chien chien, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		chienRepository.save(chien);

		URI uri = uCB.path("/rest/chien/{id}").buildAndExpand(chien.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	
	@JsonView(Views.ChienWithAnnonce.class)
	@GetMapping("/{id}")
	public ResponseEntity<Chien> findById(@PathVariable("id") Integer id) {
		Optional<Chien> opt = chienRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Chien>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		Optional<Chien> opt = chienRepository.findById(id);
		if (opt.isPresent()) {
			chienRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody Chien chien, BindingResult br, @PathVariable("id") Integer id) {
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
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
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
}
