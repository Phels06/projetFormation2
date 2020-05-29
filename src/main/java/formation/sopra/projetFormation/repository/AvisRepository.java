package formation.sopra.projetFormation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.sopra.projetFormation.entity.Avis;

public interface AvisRepository extends JpaRepository<Avis, Integer> {
}