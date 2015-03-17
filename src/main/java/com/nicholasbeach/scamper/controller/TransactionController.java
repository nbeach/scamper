package com.nicholasbeach.scamper.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.inject.Inject;

import com.nicholasbeach.scamper.persistence.TransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nicholasbeach.scamper.domain.Transaction;
import com.nicholasbeach.scamper.persistence.ResourceMapper;
import com.nicholasbeach.scamper.util.CsvMapper;


@RequestMapping(value =  "/transaction")
@RestController
public class TransactionController extends RepositoryRestfulController<Transaction> {
	
	private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Inject
    private TransactionMapper transactionRepository;

	protected ResourceMapper<Transaction> getDaoService() {
		return transactionRepository;
	}

	protected Class<Transaction> getResourceClass() {
		return Transaction.class;
	}
	
	
	@RequestMapping(value = "/csv", method = RequestMethod.POST)
	public ResponseEntity<String> create(@RequestParam("file") MultipartFile file) {
		
		 if (!file.isEmpty()) {
	  
	        logger.info("File uploaded: {}", file.getOriginalFilename());

        	String csvText;
			try {
				csvText = new String(file.getBytes(),"UTF-8");
				CsvMapper mapper = new CsvMapper();
	        	List<Transaction> transactions = mapper.mapToTransactions(csvText);
	        	for (Transaction transaction : transactions) {
                    transactionRepository.create(transaction);
				}
	        	
			} catch (UnsupportedEncodingException e) {
				logger.error("CSV mapping failed. Unsupported encoding");
				return new ResponseEntity<String>("Error: Unsuported encoding", HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (IOException e) {
				logger.error("CSV mapping failed. IO exception");
				return new ResponseEntity<String>("Error: IO exception", HttpStatus.INTERNAL_SERVER_ERROR);
			}
            	
        } else {
        	logger.error("CSV mapping failed. File empty.");
            return new ResponseEntity<String>("Error: File empty", HttpStatus.OK);
        }
		
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
}
