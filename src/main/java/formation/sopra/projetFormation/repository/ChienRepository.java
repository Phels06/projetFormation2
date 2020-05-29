package formation.sopra.projetFormation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.sopra.projetFormation.entity.Chien;

public interface ChienRepository extends JpaRepository<Chien, Integer> {
	
	List<Chien> findBySurnom(String surnom);
	List<Chien> findAll();
	
	@Query("select c from Chien c where c.id=:id")
	Optional<Chien> findByKey(@Param("id") Integer id);
	
	@Query("select c from Chien c left join fetch c.personne p where c.id=:id")
	Optional<Chien> findByIdWithPersonne(@Param("id") Integer id);
	
}