 package tn.esprit.spring.repository;


import java.util.List;

import tn.esprit.spring.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {


	@Query(value = "SELECT s FROM Stock s")
	List<Stock> getAll();
	//getStatus
	@Query("SELECT s from Stock s where s.qte< s.qteMin")
	List<Stock> retrieveStatusStock();
}
