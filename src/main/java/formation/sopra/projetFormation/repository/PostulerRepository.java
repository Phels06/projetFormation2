package formation.sopra.projetFormation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.sopra.projetFormation.entity.Postuler;
import formation.sopra.projetFormation.entity.PostulerKey;

public interface PostulerRepository extends JpaRepository<Postuler, PostulerKey> {
}