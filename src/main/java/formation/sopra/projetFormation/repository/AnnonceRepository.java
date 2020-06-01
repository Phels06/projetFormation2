package formation.sopra.projetFormation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.sopra.projetFormation.entity.Annonce;
import formation.sopra.projetFormation.entity.Personne;

public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {
	
	//annonce que j'ai posté
	@Query("select a from Annonce a where a.maitre.id=:id ")
	List<Annonce> findParMaitre(Integer id);
	
	//annonce où j'ai postuler
//	@Query("select a from Annonce a left join fetch a.postulers p where p.id.personne.id:id")
//	List<Annonce> findByPersonne(@Param("id") Integer id);
}