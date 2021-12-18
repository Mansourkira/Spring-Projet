package tn.esprit.spring.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.Rayon;
import tn.esprit.spring.repository.ProduitRepository;
import tn.esprit.spring.repository.RayonRepository;



@Service
public class RayonService {
	@Autowired
	private RayonRepository rayonRepo;
	@Autowired
	private ProduitRepository produitRepo;
	public RayonService() {
		// TODO Auto-generated constructor stub
	}
	//list stock
	public List<Rayon> retrieveAllrayon(){
		return rayonRepo.findAll();
}
	//create rayon
	public Rayon createRayon(Rayon rayon) {
		
		return rayonRepo.save(rayon);
		
	}
	//get rayon by id
	
	public Rayon  getRayonById( Long id) {

		return rayonRepo.findById(id).get();
			
	}	
	
	
	public void affectRayonToProduit(long prodId, long rayonId) {
		Rayon c = rayonRepo.findById(rayonId).get();
		Produit p = produitRepo.findById(prodId).get();
		
		p.setRayon(c);
		produitRepo.save(p);
		
	}
	
}
