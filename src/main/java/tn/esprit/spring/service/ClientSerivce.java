package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.*;

public interface ClientSerivce {
	
	List<Client> retrieveAllClients();

	Client addClient(Client c);

	void deleteClient(Long id);

	Client updateClient(Client c);

	Client retrieveClient(Long id);
	
	List<Facture> getFactures(Long id);
	
	float GetSumFactureForClient(Long id);
	
	List<Client> getClientsByCategorie(String categorie);
	
	
}
	