package tn.esprit.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;

import configuration.MyConstants;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.exception.RessourceNotFoundException;
import tn.esprit.spring.repository.StockRepository;
import tn.esprit.spring.service.StockService;







@RestController
@RequestMapping(value="/")
public class StockController {
	String msg;
	
	@Autowired
	public JavaMailSender emailSender;
	@Autowired
	private StockService stockService;
	@Autowired
private StockRepository stockRepo;
	public StockController() {
		// TODO Auto-generated constructor stub
	}
	//get all list
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="stock",produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public List<Stock> getAllStock(){
		return stockService.retrieveAllStock();
	}
	//create stock rest api
	@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="addStock",produces = "application/json",method = RequestMethod.POST)
public Stock createStock(@RequestBody Stock stock) {
	
		
		
		
		VonageClient client = VonageClient.builder().apiKey("4ebd121d").apiSecret("B9tu1NbnN7QfKqkd").build();
		
		
		TextMessage message = new TextMessage("Vonage APIs",
		        "21693271331",
		        stock.toString()
		);
	//	JSONArray jsArray = new JSONArray(stock);
System.out.println("stock::"+stock.toString());
		SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

		if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
		    System.out.println("Message sent successfully.");
		} else {
		    System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
		}
		
		
		
		
	return stockService.createStock(stock);
	 
	
}



//get stock by id rest api
	@CrossOrigin(origins = "http://localhost:4200")
	
@GetMapping("stock/{id}")

public ResponseEntity<Stock>  getStockById(@PathVariable Long id) {

	Stock stock=stockService.getStockById(id);
			//.orElseThrow(() -> new RessourceNotFoundException("stock not exist"+id) );
	 return new ResponseEntity<>(stock, HttpStatus.OK);
}




//update stock by rest api 
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="stock/{id}",produces = "application/json",method = RequestMethod.PUT)
public ResponseEntity<Stock> updateStock(@PathVariable Long id,@RequestBody Stock stockDetails){

Stock stock=stockRepo.findById(id)
		.orElseThrow(() -> new RessourceNotFoundException("stock not exist"+id) );

stock.setQte(stockDetails.getQte());
stock.setQteMin(stockDetails.getQteMin());
stock.setLibelleStock(stockDetails.getLibelleStock());
Stock updateStock=stockRepo.save(stock);
return ResponseEntity.ok(updateStock);
}







//delete stock
	@CrossOrigin(origins = "http://localhost:4200")
@DeleteMapping("/stock/{id}")
public ResponseEntity<Map<String,Boolean>> deleteStock(@PathVariable Long id){
	Stock stock=stockRepo.findById(id)
			.orElseThrow(() -> new RessourceNotFoundException("stock not exist"+id) );
	
	stockRepo.delete(stock);
	Map<String,Boolean> response=new HashMap<>();
	response.put("deleted", Boolean.TRUE);
	this.sendSimpleEmail(id);
	return ResponseEntity.ok(response);
	
}


@CrossOrigin(origins = "http://localhost:4200")
@PutMapping(value = "affectProductToStock/{stockId}/{ProcId}") 
public void affectCategoryToProduct(@PathVariable("ProcId")long ProcId, @PathVariable("stockId")long stockId)
{
//	stockService.affectProductToStock(ProcId, stockId);
}

/*
@PutMapping(value = "/disaffectStockToProduct/{ProcId}") 
public void disaffectCategoryToProduct(@PathVariable("ProcId")long ProcId)
{
	stockService.disaffectStockToProduct(ProcId);
}*/
//send simple email


@ResponseBody
@RequestMapping("/sendSimpleEmailStock")

public String sendSimpleEmail( Long id) {

    // Create a Simple MailMessage.
    SimpleMailMessage message = new SimpleMailMessage();
    
    message.setTo(MyConstants.FRIEND_EMAIL);
    message.setSubject("stock are deleted");
    message.setText("id of Stock :Id:"+id);

    // Send Message!
    this.emailSender.send(message);

    return "Email Sent!";
}


// http://localhost:8089/SpringMVC/stock/retrieveStatusStock
@GetMapping(value="retrieveStatusStock")
@ResponseBody
@ApiOperation(value = "retrieveStatusStock")
public List<Stock> retrieveStatusStock() {
	
return stockRepo.retrieveStatusStock();
}





}
