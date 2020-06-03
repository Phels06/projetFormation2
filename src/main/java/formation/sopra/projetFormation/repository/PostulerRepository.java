package formation.sopra.projetFormation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.sopra.projetFormation.entity.Postuler;
import formation.sopra.projetFormation.entity.PostulerKey;

public interface PostulerRepository extends JpaRepository<Postuler, PostulerKey> {
	
//		@Query("select p from Postuler p where p.id.personne.id=:id1 and p.id.annonce.id=:id2")
//		Optional<Postuler> findByIds(@Param("id1") Integer id1, @Param("id2") Integer id2);
}