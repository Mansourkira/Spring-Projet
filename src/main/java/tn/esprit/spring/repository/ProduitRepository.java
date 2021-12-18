package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Produit;
@Repository
public interface ProduitRepository  extends CrudRepository<Produit, Long> {

	public List<Produit> findByCategorie(CategorieProduit categorie);

	
	
}
