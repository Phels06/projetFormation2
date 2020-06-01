package formation.sopra.projetFormation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.sopra.projetFormation.entity.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {	
	
	List<Personne> findAll();
	
	List<Personne> findByPrenom(String prenom);
	
	@Query("select p from Personne p where p.adresse.ville=:ville ")
	List<Personne> findByVille(@Param("ville") String ville);
	
	@Query("select p from Personne p left join fetch p.inscription.roles where p.inscription.mail=:mail")
	public Optional<Personne> findByMailWithRoles(String mail);
	
	@Query("select p from Personne p where p.inscription.mail=:mail ")
	public Optional<Personne> findByMail(@Param("mail") String mail);
	
}