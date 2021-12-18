package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.DetailFacture;
@Repository
public interface DetaitFactureRepository extends CrudRepository<DetailFacture, Long> {


	@Query(
			  value = "SELECT * FROM detailfacture df where df.produits_id_produit=?1", 
			  nativeQuery = true)
	
	List<DetailFacture> getProd(Long p);

}
