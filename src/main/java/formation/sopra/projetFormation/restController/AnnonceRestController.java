package formation.sopra.projetFormation.restController;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import formation.sopra.projetFormation.entity.Annonce;
import formation.sopra.projetFormation.entity.view.Views;
import formation.sopra.projetFormation.repository.AnnonceRepository;


@RestController
@RequestMapping("/rest/annonce")
@CrossOrigin(origins="*")
public class AnnonceRestController {

	@Autowired
	private AnnonceRepository annonceRepository;

	@GetMapping({ "", "/" })
	@JsonView(Views.Common.class)
	public ResponseEntity<List<Annonce>> getAll() {
		return new ResponseEntity<>(annonceRepository.findAll(), HttpStatus.OK);
	}
	
	@PostMapping({ "", "/" })
	public ResponseEntity<Void> add(@Valid @RequestBody Annonce annonce, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		annonceRepository.save(annonce);

		URI uri = uCB.path("/rest/annonce/{id}").buildAndExpand(annonce.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@JsonView(Views.Common.class)
	@GetMapping("/{id}")
	public ResponseEntity<Annonce> findById(@PathVariable("id") Integer id) {
		Optional<Annonce> opt = annonceRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Annonce>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		Optional<Annonce> opt = annonceRepository.findById(id);
		if (opt.isPresent()) {
			annonceRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody Annonce annonce, BindingResult br, @PathVariable("id") Integer id) {
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Annonce> opt = annonceRepository.findById(id);
		if (opt.isPresent()) {
			Annonce annonceEnBase = opt.get();
			if(annonce.getDateAnnonce() != null) {
				annonceEnBase.setDateAnnonce(annonce.getDateAnnonce());}
			if(annonce.getNote() != null) {
				annonceEnBase.setNote(annonce.getNote());}
			if (annonce.getTarif() != null) {
				annonceEnBase.setTarif(annonce.getTarif());}
			if (annonce.getMaitre() != null) {
				annonceEnBase.setPromeneur(annonce.getPromeneur());}
			if (annonce.getChiens() != null) {
				annonceEnBase.setChiens(annonce.getChiens());}
			if (annonce.getChiens() != null) {
				annonceEnBase.setPostulers(annonce.getPostulers());}
			annonceEnBase = annonceRepository.save(annonceEnBase);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
