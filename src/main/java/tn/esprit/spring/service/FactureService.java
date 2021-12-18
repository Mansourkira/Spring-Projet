package tn.esprit.spring.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.WriterException;
import com.vonage.client.VonageClient;

import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.DetailFacture;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.QRCodeGenerator;
import tn.esprit.spring.exception.ResourceNotFoundException;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.DetaitFactureRepository;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.repository.ProduitRepository;


@Service
public class FactureService implements IServiceFacture {
	@Autowired
	FactureRepository factureRepo;
	
	@Autowired
	ClientSerivce cs;
	

	@Autowired
	ClientRepository cr;
	
	@Autowired
	ProduitRepository pr;
	
	@Autowired
	DetaitFactureRepository df;
	@Override
	public Facture GetFacture(Long id) throws ResourceNotFoundException  {
		return factureRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Facture not found for this id"));
		
	}
	

	@Override
	public List<Facture> retrieveAllFactures() {
	
		return  (List<Facture>) factureRepo.findAll();
		
		
	}
	
	// http://localhost:8089/factureupdate/status/1
	@Override
	public void cancelFacture(Long id) {
		factureRepo.findById(id).get().setActive(false);;
	}
	
	@Override
	public void ActiveFacture(Long id)
	{
		factureRepo.findById(id).get().setActive(true);
	}
	
	@Override
	public Map<String,Float> getList()
	{
		 HashMap<String, Float> map = new HashMap<>();
		 List<Client> clients = cs.retrieveAllClients();
		    
		    
			for (Client client : clients)
			{
				String n =client.getNom();
				float s = this.GetSumFactureForClient(client.getIdClient());
				
			map.put(n, s);	
			}
			return (HashMap<String,Float>)map;
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
	public List<Float> ChiffreAffaireMagasin()
	{
		float s  =0;
		List<Float> listSum = new ArrayList<>();
		
		List<Client> listclient= cs.retrieveAllClients();
		for(Client client:listclient)
		{
			s=this.GetSumFactureForClient(client.getIdClient());
			listSum.add(s);
			//Arrays.sort(null);
		}

		return listSum;
	}
	@Override
	public Map<String,Float> getSumFactureByCategorie(CategorieProduit categorie) {
		 List<Produit> produits = pr.findByCategorie(categorie);
		 HashMap<String, Float> map = new HashMap<>();

		    float s = 0;
			for (Produit p : produits)
			{
				//String nom = client.getNom();
				Produit x = pr.findById(p.getIdProduit()).get();

				CategorieProduit cap = x.getCategorie();
				
			String nom =cap.toString();
			
				List<DetailFacture> detailsF= df.getProd(x.getIdProduit());
				for (DetailFacture facture : detailsF)
				{
					 s += facture.getPrixTotal()-facture.getMontanRemise();
					 
				}
			map.put(nom, s);
			}
			return (HashMap<String,Float>)map;
	}
	@Override
	public Facture UpdateFacture(Facture f)
	{   
		
		List<DetailFacture> detailsFacture = f.getDetailsFacture();
		//System.out.println(detailsFacture);
		Facture fact = addDetailsFacture(f,detailsFacture);
		return factureRepo.save(fact);
	}


	 
	        
	      
	      
	@Override
	@Transactional
	public Facture addFacture(Facture f, Long idClient) {
		
		
		Client c = cr.findById(idClient).orElse(null);
		f.setClient(c);	
		f.setDateFacture(new Date());
		f.setActive(true);
		List<DetailFacture> detailsFacture = f.getDetailsFacture();
		System.out.println(detailsFacture);
		Facture fact = addDetailsFacture(f,detailsFacture);	
		
		
		
		

		 VonageClient client = VonageClient.builder().apiKey("60c77f35").apiSecret("ZghfP4TBCSrwmize").build();

		 com.vonage.client.sms.messages.TextMessage message = new com.vonage.client.sms.messages.TextMessage("Vonage APIs",
			        "21629598701",
			        "Votre Commande à été bien confirmé le '+new Date()'+"
			);


		 com.vonage.client.sms.SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

		 if (response.getMessages().get(0).getStatus() == com.vonage.client.sms.MessageStatus.OK) {
		     System.out.println("Message sent successfully.");
		 } else {
		     System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
		 }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return factureRepo.save(fact);
	}
	@Override
	public Facture addDetailsFacture(Facture f , List<DetailFacture> detailsFacture)
	{
		
		float montatfacture = 0;
		float MontantRemise = 0;
		for (DetailFacture detail : detailsFacture)
		{
			//Produit produit = pr.findById(detail.getProduits().getIdProduit()).orElse(null);
			Produit produit=pr.findById(detail.getProduits().getIdProduit()).orElse(null);
			float prixTotalDetail=detail.getQte() * produit.getPrixUnitaire();
			float MontantRemiseDetail=(prixTotalDetail * detail.getPourcentageRemise())/100;
			float prixTotalDetailRemise=prixTotalDetail-MontantRemiseDetail;
			detail.setMontanRemise(MontantRemiseDetail);
			detail.setPrixTotal(prixTotalDetailRemise);
			montatfacture = montatfacture + prixTotalDetailRemise;
			MontantRemise= MontantRemise + MontantRemiseDetail;
			detail.setProduits(produit);
			detail.setFactures(f);
			detail.setPourcentageRemise((MontantRemiseDetail/100));
			df.save(detail);
			
		}
		f.setMontantFacture(montatfacture);
		f.setMontantRemise(MontantRemise);
		return f;
		
	}


	@Override
	public void DeleteFacture(Long id) {
		factureRepo.deleteById(id);
	}
	@Override
	public List<Float> getAllByMonth() 
	{
		
		List<Float> listF = new ArrayList<Float>();
		for(int i=1;i<11;i++)
		{
			
			
			
			
		//	System.err.println(k);
			listF.add(factureRepo.getByMonth(i));
			
			
		}
		
		//System.err.println(listF.toString());
		return listF;
	}


	@Override
	public Map<String, Float> getSumProduit() {
		 List<Produit> produits = (List<Produit>) pr.findAll();
		 HashMap<String, Float> map = new HashMap<>();

		    float s = 0;
		    for(Produit produit :produits)
		    {
		    
		    	List<DetailFacture> detailsF= df.getProd(produit.getIdProduit());
		    	
		    	String libelle= produit.getLibelle();
		    	for (DetailFacture facture : detailsF)
				{
					 s += facture.getPrixTotal()-facture.getMontanRemise();
					 
				}
			map.put(libelle, s);
			}
			return (HashMap<String,Float>)map;
		   
	}


	@Override
	public String clearDetailsList(Long id) {
		Facture f = factureRepo.findById(id).get();
		List<DetailFacture> list = f.getDetailsFacture();
		for(DetailFacture detail: list)
		{
			Long ids = detail.getIdDetailFacture();
			System.out.println(ids);
			df.deleteById(ids);
		}
		return "ok";
	}
	
	
	@Scheduled(cron="0 0 9 25 * ?")
	public void scheduleFixedRateTask() {
		this.getSumProduit();
	    System.out.println(
	      "Produit du Mois  :" +this.getSumProduit());
	}
	

/*
	@Override
	public String getChiffreAffaireMagasin() {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd-");
		Date now = new Date();
		String msgDate = sdf.format(now);
		Float totalprix=df.getChiffreAffaireMagasin();
		
		return "Date : msgDate "+ msgDate +" ="+totalprix;
	}
*/
	
	
	
		

}
