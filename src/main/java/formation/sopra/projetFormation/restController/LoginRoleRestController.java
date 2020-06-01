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

import formation.sopra.projetFormation.entity.LoginRole;
import formation.sopra.projetFormation.entity.view.Views;
import formation.sopra.projetFormation.repository.LoginRoleRepository;

@RestController
@RequestMapping("/rest/loginRole")
@CrossOrigin(origins = "*")
public class LoginRoleRestController {

	@Autowired
	private LoginRoleRepository loginRoleRepository;

	@GetMapping({ "", "/" })
	@JsonView(Views.Common.class)
	public ResponseEntity<List<LoginRole>> getAll() {
		return new ResponseEntity<>(loginRoleRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping({ "", "/" })
	public ResponseEntity<Void> add(@Valid @RequestBody LoginRole loginRole, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		loginRoleRepository.save(loginRole);

		URI uri = uCB.path("/rest/loginRole/{id}").buildAndExpand(loginRole.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@JsonView(Views.Common.class)
	@GetMapping({ "/{id}", "/{id}/" })
	public ResponseEntity<LoginRole> findById(@PathVariable("id") Integer id) {
		Optional<LoginRole> opt = loginRoleRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<LoginRole>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping({ "/{id}", "/{id}/" })
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		Optional<LoginRole> opt = loginRoleRepository.findById(id);
		if (opt.isPresent()) {
			loginRoleRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping({ "/{id}", "/{id}/" })
	public ResponseEntity<Void> update(@Valid @RequestBody LoginRole loginRole, BindingResult br,
			@PathVariable("id") Integer id) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<LoginRole> opt = loginRoleRepository.findById(id);
		if (opt.isPresent()) {
			LoginRole loginRoleEnBase = opt.get();
			if (loginRole.getPersonne() != null) {
				loginRoleEnBase.setPersonne(loginRole.getPersonne());
			}
			if (loginRole.getRole() != null) {
				loginRoleEnBase.setRole(loginRole.getRole());
			}
			loginRoleEnBase = loginRoleRepository.save(loginRoleEnBase);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
