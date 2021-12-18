package tn.esprit.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import com.google.zxing.WriterException;

import configuration.QRCodeGenerator;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.Base64;


@RestController
public class QRCodeController {
	@Autowired
private StockService stockSer;
private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";

	
    @GetMapping(value = "/genrateQRCode/{stock_id}")
    public String getQRCode( Model model,@PathVariable("stock_id") Long stock_id){
    	  Stock	tock=stockSer.getStockById(stock_id);
      	//String a="libelle"+tock.getLibelleStock().toString();
        String medium="libelle :"+tock.getLibelleStock();
        String github="libelle :"+tock.getLibelleStock()+"qte="+tock.getQte();

        byte[] image = new byte[0];
        try {

            // Generate and Return Qr Code in Byte Array
            image = QRCodeGenerator.getQRCodeImage(medium,250,250);

            // Generate and Save Qr Code Image in static/image folder
            QRCodeGenerator.generateQRCodeImage(medium,250,250,QR_CODE_IMAGE_PATH);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);

        model.addAttribute("medium",medium);
        model.addAttribute("github",github);
        model.addAttribute("qrcode",qrcode);

        return "medium";
    }
}
