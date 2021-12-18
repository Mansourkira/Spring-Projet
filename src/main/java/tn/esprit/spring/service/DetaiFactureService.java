package tn.esprit.spring.service;

import tn.esprit.spring.entity.DetailFacture;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Produit;

public interface DetaiFactureService {
	
	public DetailFacture retreiveDetaisFacture(Long id);
	
	
	public Facture getFacture(Long id);
	
	public Produit getProduit(Long id);

}
