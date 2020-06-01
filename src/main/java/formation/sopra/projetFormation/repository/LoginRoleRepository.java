package formation.sopra.projetFormation.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.sopra.projetFormation.entity.LoginRole;

public interface LoginRoleRepository  extends JpaRepository<LoginRole, Integer> {
	
	@Query("select l.id from LoginRole l where l.personne.id=:id")
	public Integer findByIdPersonne(@Param("id") Integer id);
	
	
}
