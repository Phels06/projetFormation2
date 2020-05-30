package formation.sopra.projetFormation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import formation.sopra.projetFormation.entity.Adresse;
import formation.sopra.projetFormation.entity.Annonce;
import formation.sopra.projetFormation.entity.Civilite;
import formation.sopra.projetFormation.entity.Inscription;
import formation.sopra.projetFormation.entity.Personne;
import formation.sopra.projetFormation.entity.Postuler;
import formation.sopra.projetFormation.entity.PostulerKey;
import formation.sopra.projetFormation.repository.AnnonceRepository;
import formation.sopra.projetFormation.repository.PersonneRepository;
import formation.sopra.projetFormation.repository.PostulerRepository;

@Service
public class ConsoleService implements CommandLineRunner {

	private RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder().basicAuthentication("user", "user");

	@Autowired
	private PostulerRepository postulerRepository;
	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private AnnonceRepository annonceRepository;
	@Autowired
	private PostulerService postulerService;
	private static final String URL = "http://localhost:8080/web/rest/";

	@Override
	public void run(String... args) throws Exception {
//		Personne pe = new Personne("testPrenom", "testNom", new Inscription("toto@hotmail.fr", "AeerR"), Civilite.M,
//				new Adresse(1, "testRue", "testCP", "testVille"));
//		personneRepository.save(pe);
//		Annonce an = new Annonce();
//		annonceRepository.save(an);
//		Postuler p;
//		p = new Postuler(new PostulerKey(pe, an));
//		postulerRepository.save(p);
//
//		Annonce an2 = new Annonce();
//		annonceRepository.save(an2);
//
//		PostulerKey id = p.getId();
//		Postuler postuler = new Postuler(new PostulerKey(pe, an2));
//
//		p.setId(postuler.getId());
//
//		postulerRepository.deleteById(id);
//
//		p = postulerRepository.save(p);
	}

	
}
