package tn.esprit.spring.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.Client;


@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
	
	

	public List<Client> findByCategorie(CategorieClient categorie);
	
}
