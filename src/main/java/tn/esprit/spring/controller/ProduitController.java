package tn.esprit.spring.controller;

import java.util.List;

import tn.esprit.spring.entity.*;
import tn.esprit.spring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
@RestController
@RequestMapping("/")
public class ProduitController {
@Autowired
 ProduitService produitServ;

	public ProduitController() {
		// TODO Auto-generated constructor stub
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="produit",produces = "application/json",method = RequestMethod.GET)
public List<Produit> retriveProduit() {
	return (List<Produit>) this.produitServ.listProduit();
	
}


//create Produit rest api
	@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="addProduit",produces = "application/json",method = RequestMethod.POST)
public Produit createProduit(@RequestBody Produit produit) {
		
return produitServ.createProduit(produit);

}



//get Produit by id rest api
@GetMapping("produit/{id}")

public ResponseEntity<Produit>  getProduitById(@PathVariable Long id) {

	Produit produit=produitServ.getProduitById(id);
		//.orElseThrow(() -> new RessourceNotFoundException("Produit not exist"+id) );
 return new ResponseEntity<>(produit, HttpStatus.OK);
}
@CrossOrigin(origins = "http://localhost:4200")
@PutMapping(value = "affectRayonToProduit/{ProcId}/{rayonId}") 
public void affectRayonToProduit(@PathVariable("ProcId")long ProcId, @PathVariable("rayonId")long rayonId)
{
	produitServ. affectRayonToProduit(ProcId, rayonId);
}

}
