package tn.esprit.spring.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.Rayon;
import tn.esprit.spring.repository.ProduitRepository;
import tn.esprit.spring.repository.RayonRepository;




@Service
public class ProduitService {
	@Autowired
	public ProduitRepository produitRepo;
	@Autowired
public RayonRepository rayonRepo;

	
	public ProduitService() {
		// TODO Auto-generated constructor stub
	}

	public Set<Produit> listProduit(){
		return (Set<Produit>) produitRepo.findAll();
			
	}
	
	//create Produit
	public Produit createProduit(Produit produit) {
		
		return produitRepo.save(produit);
		
	}
	//get Produit by id
	
	public Produit  getProduitById( Long id) {

		return produitRepo.findById(id).get();
			
	}	
	
	public void affectRayonToProduit(long ProcId, long rayonId) {
		Rayon r = rayonRepo.findById(rayonId).get();
		Produit p = produitRepo.findById(ProcId).get();
		
		p.setRayon(r);
		produitRepo.save(p);
		
	}

	
}
