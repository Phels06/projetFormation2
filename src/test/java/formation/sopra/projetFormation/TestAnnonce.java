package formation.sopra.projetFormation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import formation.sopra.projetFormation.entity.Annonce;
import formation.sopra.projetFormation.entity.Chien;
import formation.sopra.projetFormation.entity.Note;
import formation.sopra.projetFormation.entity.Personne;
import formation.sopra.projetFormation.repository.AnnonceRepository;
import formation.sopra.projetFormation.service.AnnonceService;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestAnnonce {

	@Autowired
	private AnnonceRepository annonceRepository;
	@Autowired
	private AnnonceService annonceService;
	private static SimpleDateFormat sdf;

	@BeforeClass
	public static void initDateFormat() {
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}

/////////////////////////////////////////REPOSITORY/////////////////////////////////////

	@Test
	public void testAnnonceRepository() {
		assertNotNull(annonceRepository);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testInsertAnnonce() {
		Annonce a;
		try {
			a = new Annonce(sdf.parse("11/05/2020"), Note.N3);
			
			assertNull(a.getId());
			annonceRepository.save(a);
			assertNotNull(a.getId());
			assertTrue(annonceRepository.findById(a.getId()).isPresent());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

//////////////////////////////////SERVICE//////////////////////////////

	@Test
	public void testAnnonceService() {
		assertNotNull(annonceService);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testServiceAjoutAnnonce() {
		Annonce a;
		try {
			a = new Annonce(sdf.parse("11/05/2020"), Note.N3);
			a.setMaitre(new Personne());
			Set<Chien> chiens = new HashSet<>();
			chiens.add(new Chien());
			a.setChiens(chiens);
			assertTrue(annonceService.ajout(a));
			assertNotNull(a.getId());

			assertTrue(annonceRepository.findById(a.getId()).isPresent());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testServiceAjoutAnnonceObjetIncompletDate() {
		Annonce a;

		a = new Annonce(null, Note.N3);
		a.setMaitre(new Personne());
		Set<Chien> chiens = new HashSet<>();
		chiens.add(new Chien());
		a.setChiens(chiens);
		assertFalse(annonceService.ajout(a));
		assertNull(a.getId());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testServiceAjoutAnnonceObjetIncompletNote() {
		Annonce a;
		try {
			a = new Annonce(sdf.parse("11/05/2020"), null);
			a.setMaitre(new Personne());
			Set<Chien> chiens = new HashSet<>();
			chiens.add(new Chien());
			a.setChiens(chiens);
			assertTrue(annonceService.ajout(a));
			assertNotNull(a.getId());

			assertTrue(annonceRepository.findById(a.getId()).isPresent());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testServiceAjoutAnnonceImcompletMaitre() {
		Annonce a;
		try {
			a = new Annonce(sdf.parse("11/05/2020"), Note.N3);
			Set<Chien> chiens = new HashSet<>();
			chiens.add(new Chien());
			a.setChiens(chiens);
			assertFalse(annonceService.ajout(a));
			assertNull(a.getId());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testServiceAjoutAnnonceImcompletChiens() {
		Annonce a;
		try {
			a = new Annonce(sdf.parse("11/05/2020"), Note.N3);
			a.setMaitre(new Personne());
			assertFalse(annonceService.ajout(a));
			assertNull(a.getId());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testServiceAnnonceMAJ() {
		Annonce a;
		try {
			a = new Annonce(sdf.parse("11/05/2020"), Note.N3);
			a.setMaitre(new Personne());
			Set<Chien> chiens = new HashSet<>();
			chiens.add(new Chien());
			a.setChiens(chiens);
			assertTrue(annonceService.ajout(a));

			assertTrue(annonceRepository.findById(a.getId()).isPresent());
			a.setDateAnnonce(sdf.parse("18/05/2020"));
			a.setNote(Note.N5);
			
			annonceService.miseAjour(a);
			assertEquals(annonceRepository.findById(a.getId()).get(),a);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testServiceAnnonceRecherche() {
		Annonce a;
		try {
			a = new Annonce(sdf.parse("11/05/2020"), Note.N3);
			annonceRepository.save(a);

			assertEquals(annonceRepository.findById(a.getId()).get(), annonceService.recherche(a.getId()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
