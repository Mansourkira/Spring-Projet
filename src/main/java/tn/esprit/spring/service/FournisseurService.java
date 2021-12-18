package  tn.esprit.spring.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tn.esprit.spring.entity.*;

import tn.esprit.spring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class FournisseurService {
	Set<Fournisseur> hset = new HashSet<>();

	
	@Autowired
	private FournisseurRepository founisseurRepository;
@Autowired
private ProduitRepository produitRepo;
	public FournisseurService() {
		// TODO Auto-generated constructor stub
	}

	//list Fournisseur
	public List<Fournisseur> retrieveAllFournisseur(){
		return founisseurRepository.findAll();
}
	//create Fournisseur
	public Fournisseur createFournisseur(Fournisseur fournisseur) {
		
		return founisseurRepository.save(fournisseur);
		
	}
	//get Fournisseur by id
	
	public Fournisseur  getFournisseurById( Long id) {

		return founisseurRepository.findById(id).get();
			
	}	
	
	
	/*public void disaffectStockToProduct(long ProcId) {
		
		Fournisseur c = produitRepo.findById(ProcId).get().getFournisseurs();
		
		c.getProduits().remove(ProcId);
		productRepo.findById(ProcId).get().setStock(null);;
		productRepo.save(productRepo.findById(ProcId).get());
	
		
	}*/

	

public void	affectProductToFournisseur(Long ProcId, Long FourId){
	hset.clear();
		Fournisseur f = founisseurRepository.findById(FourId).get();
		Produit p = produitRepo.findById(ProcId).get();
		hset.add(f);
	p.setFournisseur(hset);
		produitRepo.save(p);
	}




//public List<Fournisseur> getFournisseurByName(String name) {

 // return (List<Fournisseur>) founisseurRepository.getFournisseurByNameJPQL(name);


//}



public Page<Fournisseur> chercherProduits(PageRequest page) {
	return founisseurRepository.chercherProduits(page);
}

}
