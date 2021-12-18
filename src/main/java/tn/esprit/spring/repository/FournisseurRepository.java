package tn.esprit.spring.repository;

import java.awt.print.Pageable;

import tn.esprit.spring.entity.Fournisseur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {



//	public Fournisseur getFournisseurByNameJPQL(@Param("name") String name);
	
	@Query("select p from Stock p")
	public Page<Fournisseur> chercherProduits(PageRequest	 page);
}
