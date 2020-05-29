package formation.sopra.projetFormation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.sopra.projetFormation.entity.Annonce;

public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {
}