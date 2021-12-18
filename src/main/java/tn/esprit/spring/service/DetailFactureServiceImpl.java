package tn.esprit.spring.service;


import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entity.DetailFacture;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.DetaitFactureRepository;

public class DetailFactureServiceImpl implements DetaiFactureService {

	@Autowired
	DetaitFactureRepository detailFa;
	
	

	@Override
	public Facture getFacture(Long id) {
		DetailFacture d =detailFa.findById(id).get();
		Facture f =d.getFactures();
		return f;
	}

	@Override
	public Produit getProduit(Long id) {
		DetailFacture d =detailFa.findById(id).get();
		Produit p =d.getProduits();
		return p;
	}

	@Override
	public DetailFacture retreiveDetaisFacture(Long id) {
		DetailFacture df = detailFa.findById(id).orElse(null);
		
		return df;
	}
	
	
	
	
	

}
