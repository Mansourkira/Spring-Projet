package tn.esprit.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import tn.esprit.spring.entity.Rayon;
import tn.esprit.spring.exception.RessourceNotFoundException;
import tn.esprit.spring.repository.RayonRepository;
import tn.esprit.spring.service.RayonService;

@RestController

@RequestMapping(value="/")
public class RayonController {
@Autowired
private RayonService rayonSer;
@Autowired
private RayonRepository rayonRepo;
	public RayonController() {
		// TODO Auto-generated constructor stub
	}
	//get all list
	@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(value="rayon",produces = "application/json",method = RequestMethod.GET)
		@ResponseBody
		public List<Rayon> getAllRayon(){
			return rayonSer.retrieveAllrayon();
		}
		//create rayon rest api
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="addRayon",produces = "application/json",method = RequestMethod.POST)
	public Rayon createRayon(@RequestBody Rayon rayon) {
		
		return rayonSer.createRayon(rayon);
		
	}








	//update Rayon by rest api 
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("rayon/{id}")
	public ResponseEntity<Rayon> updateRayon(@PathVariable Long id,@RequestBody Rayon rayonDetails){

		Rayon rayon=rayonRepo.findById(id)
			.orElseThrow(() -> new RessourceNotFoundException("Rayon not exist"+id) );
	rayon.setCode(rayonDetails.getCode());
	rayon.setLibelle(rayonDetails.getLibelle());
	rayon.setProduits(rayonDetails.getProduits());

	Rayon updateRayon=rayonRepo.save(rayon);
	return ResponseEntity.ok(updateRayon);
	}







	//deleteRayon
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("rayon/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteRayon(@PathVariable Long id){
		Rayon rayon=rayonRepo.findById(id)
				.orElseThrow(() -> new RessourceNotFoundException("Rayon not exist"+id) );
		
		rayonRepo.delete(rayon);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
	
	
	
	//get stock by id rest api
		@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("rayon/{id}")

	public ResponseEntity<Rayon>  getRayonById(@PathVariable Long id) {

		Rayon rayon=rayonSer.getRayonById(id);
				//.orElseThrow(() -> new RessourceNotFoundException("stock not exist"+id) );
		 return new ResponseEntity<>(rayon, HttpStatus.OK);
	}


}
