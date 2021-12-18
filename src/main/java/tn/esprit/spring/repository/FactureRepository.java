package tn.esprit.spring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.Facture;

@Repository
public interface FactureRepository extends JpaRepository<Facture,Long> {

	
	
	
	@Query("select sum(f.montantFacture) from Facture f where month(f.dateFacture) = ?1")
	public Float getByMonth(int month);
	
	
	

}

