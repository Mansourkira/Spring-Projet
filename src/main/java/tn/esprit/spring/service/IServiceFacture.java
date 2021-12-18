package tn.esprit.spring.service;
	
	import java.util.List;
import java.util.Map;

import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.DetailFacture;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.exception.ResourceNotFoundException;
	
	public interface IServiceFacture {
		
		
	Facture addFacture(Facture f, Long idClient);
		
	Facture GetFacture(Long id) throws ResourceNotFoundException;
	
	List<Facture> retrieveAllFactures();
	
	
	void cancelFacture(Long id);
	
	void ActiveFacture(Long id);
	
	float GetSumFactureForClient(Long id);

	Map<String,Float> getList();

	public Map<String , Float> getSumFactureByCategorie(CategorieProduit categorie);
	
	
	public Map<String , Float> getSumProduit();

	
	
	public Facture addDetailsFacture(Facture f , List<DetailFacture> detailsFacture);
	
	
	
	
	public void DeleteFacture(Long id);

	Facture UpdateFacture(Facture f);
	
	List<Float> getAllByMonth();
	//public String getChiffreAffaireMagasin();

	List<Float> ChiffreAffaireMagasin();
	
	
	String clearDetailsList(Long id);
	
}





