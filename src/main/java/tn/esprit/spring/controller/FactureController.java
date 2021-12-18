package tn.esprit.spring.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.QRCodeGenerator;
import tn.esprit.spring.exception.ResourceNotFoundException;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.repository.ProduitRepository;
import tn.esprit.spring.service.IServiceFacture;


import com.google.zxing.WriterException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/facture")

public class FactureController {
	
	@Autowired
	IServiceFacture factureSer;
	

	@Autowired
	ProduitRepository pr;

	@Autowired
	FactureRepository fact;
	
	
	@Autowired
	ClientRepository cr;
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCodse.png";
	
		 //http://localhost:8080/getFacture
		@GetMapping(value = "/getFacture/{idFacture}")
	    @ResponseBody
		public Facture getFacture(@PathVariable("idFacture") Long id) throws ResourceNotFoundException  {
			
			return factureSer.GetFacture(id);
		}
		
		 	
		@GetMapping(value = "/getFactures")
	    @ResponseBody
		public List<Facture> getFactures() throws ResourceNotFoundException {
			return factureSer.retrieveAllFactures();		
		}
		
		
		@PutMapping(value = "/updateFacture")
		@ResponseBody
		public Facture updateFacture(@RequestBody Facture f)
		{
			return factureSer.UpdateFacture(f);
		}
			
		
		/*-------------------------------------------------------*/
		
		@PutMapping(value = "/cancelFacture/{idFacture}")
		@ResponseBody
		public void cancelFacture(@PathVariable("idFacture") Long id) throws ResourceNotFoundException
		{
			factureSer.cancelFacture(id);
			System.out.println(" Facture Canceled ! ");
		}
		@PutMapping(value = "/activeFacture/{idFacture}")
		@ResponseBody
		public String ActiveFacture(@PathVariable("idFacture") Long id) throws ResourceNotFoundException
		{
			factureSer.cancelFacture(id);
			return "Facture Activated ! ";
			
		}

		@PostMapping(value="/addfacture/{idClient}")
		@ResponseBody
		public String addFactures(@RequestBody Facture f,@PathVariable("idClient") Long idclient)
		{
			 factureSer.addFacture(f,idclient);
			 return "jawha bhy";
		}
		

		@GetMapping(value ="/sum/{idclient}")
		@ResponseBody
		public float GetSumFacture(@PathVariable("idclient") Long id)throws ResourceNotFoundException
		{
			return factureSer.GetSumFactureForClient(id);
		}
	
			
		
		@GetMapping(value ="/getlist")
		@ResponseBody
		Map<String,Float> getListMap()
		{
			return factureSer.getList();	
		}
		
		
		
		@GetMapping(value ="/getByCategie")
		@ResponseBody
		Map<String,Float> getByCa()
		{			
			 HashMap<String, Float> map = new HashMap<>();
				map.putAll(factureSer.getSumFactureByCategorie(CategorieProduit.Alimentaire));
				map.putAll(factureSer.getSumFactureByCategorie(CategorieProduit.Electromenager));
				map.putAll(factureSer.getSumFactureByCategorie(CategorieProduit.Quincaillerie));
			return map;
		}
		
		@GetMapping(value ="/getSumProduit")
		@ResponseBody
		Map<String,Float> getSumProduit()
		{			
				
			 return factureSer.getSumProduit();
			
		}
		@GetMapping(value ="/getProduits")
		@ResponseBody
		public List<Produit> getProduits()
		{
			return (List<Produit>) pr.findAll();
 
		}
		@GetMapping(value ="/getClients")
		@ResponseBody
		public List<Client> getClients()throws ResourceNotFoundException
		{
			return (List<Client>) cr.findAll();

		}
		@DeleteMapping("/deleteById/{id}")
	    @ResponseBody
	    public void DeleteFacture(@PathVariable("id") Long id)throws ResourceNotFoundException
	    {
			factureSer.DeleteFacture(id);
	    }
		
		
		@GetMapping(value="/mois")
		@ResponseBody	
		public List<Float> getChiffreAffaireForAllMonth()
		{
			
			return (List<Float>)factureSer.getAllByMonth();
		}
		
		
		@GetMapping(value="/sum")
		@ResponseBody	
		public List<Float> gettopClient()
		{
			
			return factureSer.ChiffreAffaireMagasin();
		}
		
		@DeleteMapping(value="/clearDetails/{id}")
		@ResponseBody	
		public String clearDetailsList(@PathVariable Long id )throws ResourceNotFoundException
		{
			
			return factureSer.clearDetailsList(id);
		}
		
		   
		@GetMapping(value="/qrcode/{idFacture}")
		 public String getQRCode(Model model,@PathVariable("idFacture") Long id){
			Facture f =fact.findById(id).get();
		
		        String medium="https://rahul26021999.medium.com/";
		        String github= "Client : "+f.getClient().getNom()+"Montant Facture : "+f.getMontantFacture()+"Date Facture :"+f.getDateFacture();
		        		
		

		        byte[] image = {1,2,3,4,5};	
		        try {

		            // Generate and Return Qr Code in Byte Array
		            image = QRCodeGenerator.getQRCodeImage(medium,250,250);

		            // Generate and Save Qr Code Image in static/image folder
		            QRCodeGenerator.generateQRCodeImage(github,250,250,QR_CODE_IMAGE_PATH);

		        } catch (WriterException | IOException e) {
		            e.printStackTrace();
		        }
		        // Convert Byte Array into Base64 Encode String
		        String qrcode = Base64.getEncoder().encodeToString(image);

		        model.addAttribute("medium",medium);
		        model.addAttribute("github",github);
		        model.addAttribute("qrcode",qrcode);

		        return "qrcode";
		    }
		
		

		@RequestMapping(value = "/pdfreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
		public ResponseEntity<InputStreamResource> citiesReport() throws IOException {

			List<Facture> cities = (List<Facture>) fact.findAll();

			ByteArrayInputStream bis = tn.esprit.spring.pdf.GeneratePdfReport.citiesReport(cities);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

		
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bis));
		}
		
		
		
	
}
