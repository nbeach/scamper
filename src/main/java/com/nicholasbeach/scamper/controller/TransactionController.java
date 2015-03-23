package com.nicholasbeach.scamper.controller;

import javax.inject.Inject;

import com.nicholasbeach.scamper.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.nicholasbeach.scamper.domain.Transaction;



@RequestMapping(value =  "/transaction")
@RestController
public class TransactionController extends AbstractRestfulController<Transaction> {

    @Inject TransactionService transactionService;

	protected TransactionService getService() {
		return transactionService;
	}

	protected Class<Transaction> getResourceClass() {
		return Transaction.class;
	}
	
	
	@RequestMapping(value = "/csv", method = RequestMethod.POST)
	public ResponseEntity<String> create(@RequestParam("file") MultipartFile file) {
		
		 if (!file.isEmpty()) {
	  
	        log.info("File uploaded: {}", file.getOriginalFilename());

            transactionService.importCsvTransactions(file);
            	
        } else {
        	log.error("CSV mapping failed. File empty.");
            return new ResponseEntity<String>("Error: File empty", HttpStatus.OK);
        }
		
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
}
