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
import formation.sopra.projetFormation.entity.Avis;
import formation.sopra.projetFormation.entity.view.Views;
import formation.sopra.projetFormation.repository.AvisRepository;


@RestController
@RequestMapping("/rest/avis")
@CrossOrigin(origins = "*")
public class AvisRestController {

	@Autowired
	private AvisRepository avisRepository;
	
	
	@GetMapping({ "", "/" })
	@JsonView(Views.Common.class)
	public ResponseEntity<List<Avis>> getAllAvis() {
		return new ResponseEntity<>(avisRepository.findAll(), HttpStatus.OK);
	}
	
	
	@PostMapping({ "", "/" })
	public ResponseEntity<Void> addAvis(@Valid @RequestBody Avis avis, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		avisRepository.save(avis);

		URI uri = uCB.path("/rest/avis/{id}").buildAndExpand(avis.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	
	@JsonView(Views.Common.class)
	@GetMapping("/{id}")
	public ResponseEntity<Avis> findById(@PathVariable("id") Integer id) {
		Optional<Avis> opt = avisRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Avis>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		Optional<Avis> opt = avisRepository.findById(id);
		if (opt.isPresent()) {
			avisRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody Avis avis, BindingResult br, @PathVariable("id") Integer id) {
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Avis> opt = avisRepository.findById(id);
		if (opt.isPresent()) {
			Avis avisEnBase = opt.get();
			avisEnBase.setAvisMaitre(avis.getAvisMaitre());
			avisEnBase = avisRepository.save(avisEnBase);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
