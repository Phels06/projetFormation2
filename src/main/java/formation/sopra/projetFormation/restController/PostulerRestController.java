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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import formation.sopra.projetFormation.entity.Annonce;
import formation.sopra.projetFormation.entity.Personne;
import formation.sopra.projetFormation.entity.Postuler;
import formation.sopra.projetFormation.entity.PostulerKey;
import formation.sopra.projetFormation.entity.view.Views;
import formation.sopra.projetFormation.repository.AnnonceRepository;
import formation.sopra.projetFormation.repository.PersonneRepository;
import formation.sopra.projetFormation.repository.PostulerRepository;

@RestController
@RequestMapping("/rest/postuler")
@CrossOrigin(origins = "*")
public class PostulerRestController {

	@Autowired
	private PostulerRepository postulerRepository;
	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private AnnonceRepository annonceRepository;

	@GetMapping({ "", "/" })
	@JsonView(Views.Common.class)
	public ResponseEntity<List<Postuler>> getAll() {
		return new ResponseEntity<>(postulerRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping({ "", "/" })
	public ResponseEntity<Void> add(@Valid @RequestBody Postuler postuler, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		postulerRepository.save(postuler);

		URI uri = uCB.path("/rest/postuler/{id}").buildAndExpand(postuler.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@JsonView(Views.Common.class)
	@GetMapping({ "/{id1}/{id2}", "/{id1}/{id2}/" })
	public ResponseEntity<Postuler> findById(@PathVariable("id1") Integer id1, @PathVariable("id2") Integer id2) {
		Optional<Personne> opt1 = personneRepository.findById(id1);
		Optional<Annonce> opt2 = annonceRepository.findById(id2);
		if (opt1.isPresent() && opt2.isPresent()) {
			PostulerKey id = new PostulerKey(opt1.get(), opt2.get());
			Optional<Postuler> opt = postulerRepository.findById(id);
			if (opt.isPresent()) {
				return new ResponseEntity<Postuler>(opt.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping({ "/{id1}/{id2}", "/{id1}/{id2}/" })
	public ResponseEntity<Void> delete(@PathVariable("id1") Integer id1, @PathVariable("id2") Integer id2) {
		Optional<Personne> opt1 = personneRepository.findById(id1);
		Optional<Annonce> opt2 = annonceRepository.findById(id2);
		if (opt1.isPresent() && opt2.isPresent()) {
			PostulerKey id = new PostulerKey(opt1.get(), opt2.get());
			Optional<Postuler> opt = postulerRepository.findById(id);
			if (opt.isPresent()) {
				postulerRepository.deleteById(id);
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

//	@PutMapping({"/{id}","/{id}/"})
//	public ResponseEntity<Void> update(@Valid @RequestBody Postuler postuler, BindingResult br, @PathVariable("id") PostulerKey id) {
//		if(br.hasErrors()) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		Optional<Postuler> opt = postulerRepository.findById(id);
//		if (opt.isPresent()) {
//			Postuler postulerEnBase = opt.get();
//			postulerEnBase.setId(postuler.getId());
//			postulerEnBase.setVersion(postuler.getVersion()+1);
//			postulerRepository.deleteById(id);
//			postulerEnBase = postulerRepository.save(postulerEnBase);
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	}
}
