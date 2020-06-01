package formation.sopra.projetFormation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.sopra.projetFormation.entity.Annonce;
import formation.sopra.projetFormation.entity.Personne;

public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {

	// annonce que j'ai posté
	@Query("select a from Annonce a where a.maitre.id=:id ")
	List<Annonce> findByMaitreSId(@Param("id") Integer id);

	// annonce que j'ai été confirmé
	@Query("select a from Annonce a where a.promeneur.id=:id ")
	List<Annonce> findByPromeneurSId(@Param("id") Integer id);

	// annonce où j'ai postuler
//	@Query("select a from Annonce a left join fetch a.postulers p where p.id.personne.id:id")
//	List<Annonce> findByPersonne(@Param("id") Integer id);

	@Query("select a from Annonce a where a.maitre.inscription.ville=:ville")
	List<Annonce> findByVille(@Param("ville") String ville);
}