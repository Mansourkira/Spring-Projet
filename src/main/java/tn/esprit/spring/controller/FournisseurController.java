package tn.esprit.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.exception.RessourceNotFoundException;
import tn.esprit.spring.repository.*;
import tn.esprit.spring.service.FournisseurService;
import tn.esprit.spring.service.ProduitService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import configuration.MyConstants;
@RestController
@RequestMapping(value="/")
public class FournisseurController {

	public FournisseurController() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	private FournisseurService FournisseurService;
	@Autowired
private FournisseurRepository FournisseurRepo;
	@Autowired
public ProduitService ProdSer;
	@Autowired
	public JavaMailSender emailSender;
	//get all list
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="fournisseur",produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public List<Fournisseur> getAllFournisseur(){
		return FournisseurService.retrieveAllFournisseur();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	//create fournisseur rest api
@RequestMapping(value="addFournisseur",produces = "application/json",method = RequestMethod.POST)
public Fournisseur createFournisseur(@RequestBody Fournisseur fournisseur) {
	
	return FournisseurService.createFournisseur(fournisseur);
	
}



//get Fournisseur by id rest api
	@CrossOrigin(origins = "http://localhost:4200")
@GetMapping("fournisseur/{id}")

public ResponseEntity<Fournisseur>  getFournisseurById(@PathVariable Long id) {

	Fournisseur fournisseur=FournisseurService.getFournisseurById(id);
			//.orElseThrow(() -> new RessourceNotFoundException("stock not exist"+id) );
	 return new ResponseEntity<>(fournisseur, HttpStatus.OK);
}




//update stock by rest api 
@CrossOrigin(origins = "http://localhost:4200")
@PutMapping("fournisseur/{id}")
public ResponseEntity<Fournisseur> updateStock(@PathVariable Long id,@RequestBody Fournisseur fournisseurDetails){

	Fournisseur fournisseur=FournisseurRepo.findById(id)
		.orElseThrow(() -> new RessourceNotFoundException("Fournisseur not exist"+id) );
	fournisseur.setCode(fournisseurDetails.getCode());
fournisseur.setLibelle(fournisseurDetails.getLibelle());


Fournisseur updatefournisseur=FournisseurRepo.save(fournisseur);
return ResponseEntity.ok(updatefournisseur);
}







//delete stock
@CrossOrigin(origins = "http://localhost:4200")
@DeleteMapping("fournisseur/{id}")
public ResponseEntity<Map<String,Boolean>> deletefournisseur(@PathVariable Long id){
	Fournisseur fournisseur=FournisseurRepo.findById(id)
			.orElseThrow(() -> new RessourceNotFoundException("Fournisseur not exist"+id) );
	
	FournisseurRepo.delete(fournisseur);
	Map<String,Boolean> response=new HashMap<>();
	response.put("deleted", Boolean.TRUE);
	this.sendSimpleEmail(id);
	return ResponseEntity.ok(response);
	
}

@CrossOrigin(origins = "http://localhost:4200")
@PutMapping(value = "affectProductToFournisseur/{ProcId}/{FourId}") 
public void affectFournisseurToProduct(@PathVariable("ProcId")long ProcId, @PathVariable("FourId")long FourId)
{
	FournisseurService.affectProductToFournisseur(ProcId, FourId);
	
	this.sendSimpleEmail(ProcId);
}

/*
@PutMapping(value = "/disaffectStockToProduct/{ProcId}") 
public void disaffectCategoryToProduct(@PathVariable("ProcId")long ProcId)
{
	stockService.disaffectStockToProduct(ProcId);
}

*/



@CrossOrigin(origins = "http://localhost:4200")
@ResponseBody
@RequestMapping("/sendSimpleEmail")

public String sendSimpleEmail( Long id) {

    // Create a Simple MailMessage.
    SimpleMailMessage message = new SimpleMailMessage();
    
    message.setTo(MyConstants.FRIEND_EMAIL);
    message.setSubject("Fournisseur are deleted");
    message.setText("id of Fournisseur :Id:"+id);

    // Send Message!
    this.emailSender.send(message);

    return "Email Sent!";
}


//search Fournisseur


}
