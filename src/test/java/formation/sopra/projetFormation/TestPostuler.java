package formation.sopra.projetFormation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
import formation.sopra.projetFormation.service.PostulerService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPostuler {

	@Autowired
	private PostulerRepository postulerRepository;
	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private AnnonceRepository annonceRepository;
	@Autowired
	private PostulerService postulerService;

/////////////////////////////////////////REPOSITORY/////////////////////////////////////

	@Test
	public void testPostulerRepository() {
		assertNotNull(postulerRepository);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testInsertPostuler() {
		Personne pe = new Personne("testPrenom", "testNom", new Inscription("toto@hotmail.fr", "AeerR"), Civilite.M,
				new Adresse(1, "testRue", "testCP", "testVille"));
		personneRepository.save(pe);
		Annonce an = new Annonce();
		annonceRepository.save(an);
		Postuler p;
		p = new Postuler(new PostulerKey(pe, an));

		postulerRepository.save(p);
		assertNotNull(p.getId());
		assertTrue(postulerRepository.findById(p.getId()).isPresent());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testDeletePostuler() {
		Personne pe = new Personne("testPrenom", "testNom", new Inscription("toto@hotmail.fr", "AeerR"), Civilite.M,
				new Adresse(1, "testRue", "testCP", "testVille"));
		personneRepository.save(pe);
		Annonce an = new Annonce();
		annonceRepository.save(an);
		Postuler p;
		p = new Postuler(new PostulerKey(pe, an));
		postulerRepository.save(p);
		assertNotNull(p.getId());
		assertTrue(postulerRepository.findById(p.getId()).isPresent());

		Annonce an2 = new Annonce();
		annonceRepository.save(an2);

		PostulerKey id = p.getId();
		Postuler postuler = new Postuler(new PostulerKey(pe, an2));

		p.setId(postuler.getId());
		System.out.println(p.getVersion());
		postulerRepository.deleteById(id);
		System.out.println("avant save"+p.getVersion());
		p = postulerRepository.save(p);
		System.out.println("apres save"+p.getVersion());

		assertNotNull(p.getId());
		assertTrue(postulerRepository.findById(p.getId()).isPresent());
		assertTrue(p.getId() == postuler.getId());

	}

//////////////////////////////////SERVICE//////////////////////////////

	@Test
	public void testPostulerService() {
		assertNotNull(postulerService);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testServiceAjoutPostuler() {
		Personne pe = new Personne("testPrenom", "testNom", new Inscription("toto@hotmail.fr", "AeerR"), Civilite.M,
				new Adresse(1, "testRue", "testCP", "testVille"));
		personneRepository.save(pe);
		Annonce an = new Annonce();
		annonceRepository.save(an);
		Postuler p;
		p = new Postuler(new PostulerKey(pe, an));

		assertTrue(postulerService.ajout(p));
		assertNotNull(p.getId());

		assertTrue(postulerRepository.findById(p.getId()).isPresent());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testServicePostulerRecherche() {
		Personne pe = new Personne("testPrenom", "testNom", new Inscription("toto@hotmail.fr", "AeerR"), Civilite.M,
				new Adresse(1, "testRue", "testCP", "testVille"));
		personneRepository.save(pe);
		Annonce an = new Annonce();
		annonceRepository.save(an);

		Postuler p;
		p = new Postuler(new PostulerKey(pe, an));
		System.out.println(postulerRepository.save(p));

		assertEquals(postulerRepository.findById(p.getId()).get(), postulerService.recherche(p.getId()));
	}

}
