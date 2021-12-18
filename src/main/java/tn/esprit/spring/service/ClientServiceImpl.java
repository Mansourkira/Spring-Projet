package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.FactureRepository;

@Service
public class ClientServiceImpl implements ClientSerivce {
	@Autowired
	ClientRepository cr;
	
	@Autowired
	FactureRepository facturerepository;
	@Override
	public List<Client> retrieveAllClients() {
		
		
		return (List<Client>) cr.findAll();

			}

	@Override
	public Client addClient(Client c) {
			
		return cr.save(c);
	}

	@Override
	public void deleteClient(Long id) {
		 cr.deleteById(id);
		System.out.println("Deleted Succeffly");
		
	}

	@Override
	public Client updateClient(Client c) {
		return cr.save(c);
	}

	@Override
	public Client retrieveClient(Long id) {
	return cr.findById(id).get();
	}

	@Override
	public List<Facture> getFactures(Long id) {
		
		Client c = cr.findById(id).get();
		List<Facture> factures = c.getFacture();
		return factures;
		
	}

	@Override
	public float GetSumFactureForClient(Long id) {
		float sum=0;
		Client c = cr.findById(id).get();
		List<Facture> factures = c.getFacture();
		for (Facture facture : factures)
		{
			 sum += facture.getMontantFacture();
		}
		return sum;
	}

	@Override
	public List<Client> getClientsByCategorie(String categorie) {
		
		 List<Client> clients = this.retrieveAllClients();
		 
		return null;
	}
	
	


	
	

}
