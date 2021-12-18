package tn.esprit.spring.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.repository.ProduitRepository;
import tn.esprit.spring.repository.StockRepository;



@Aspect
@Slf4j
@Service
public class StockService {


	@Autowired
	private StockRepository stockRepository;
public  StockService() {         }
	//list stock
	public List<Stock> retrieveAllStock(){
		return stockRepository.getAll();
}
	//create stock
	public Stock createStock(Stock stock) {
		
		return stockRepository.save(stock);
		
	}
	//get stock by id
	
	public Stock  getStockById( Long id) {

		return stockRepository.findById(id).get();
			
	}	
	
	
	
	
	@Scheduled(cron = "*/60 * * * * *" )
public String retrieveStatusStock() {
		SimpleDateFormat sdf = new 	SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String msgDate = sdf.format(now);
		String finalMessage = "";
		String newLine = System.getProperty("line.separator");
		List<Stock> stocks = stockRepository.retrieveStatusStock();
		for (int i= 0; i < stocks.size(); i++) {
			finalMessage = newLine + finalMessage +  msgDate + newLine + ": le produit"
					+stocks.get(i).getLibelleStock() +  "a un stock de" + stocks.get(i).getQte()
					+ "infÃ©rieur Ã  la quantitÃ© minimale a ne pas dÃ©passer de " + stocks.get(i).getQteMin()
					+newLine;
			
		}
		log.info(finalMessage);
		return finalMessage;
	
	}
	
	/*public void disaffectStockToProduct(long ProcId) {
	
		Stock c = productRepo.findById(ProcId).get().getStock();
		
		c.getProduits().remove(ProcId);
		productRepo.findById(ProcId).get().setStock(null);;
		productRepo.save(productRepo.findById(ProcId).get());
	
		
	}*/

	
/*
public void	affectProductToStock(Long ProcId, Long stockId){
		
		Stock s = stockRepository.findById(stockId).get();
		Produit p = productRepo.findById(ProcId).get();
		
		p.setStock(s);
		productRepo.save(p);
	}	
*/
	
	//paginator
	public Page<Stock> findRayonWithPagination(int offset, int pageSize) {
		Page<Stock> stocks = stockRepository.findAll(PageRequest.of(offset, pageSize));
		return stocks;
	}
	//paginator

	public Page<Stock> findRayonWithPaginationAndSorting(int offset, int pageSize, String field) {
		// TODO Auto-generated method stub
		return stockRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
	}
}
