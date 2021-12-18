package tn.esprit.spring.repository;

import tn.esprit.spring.entity.Rayon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface RayonRepository extends JpaRepository<Rayon, Long> {

	/*@Query("Select "
			+ "DISTINCT cat from Rayon cat "
			+ "where cat.libelle=:libelle")
	public Rayon getRayonByNameJPQL(@Param("libelle") String libelle);*/
}
