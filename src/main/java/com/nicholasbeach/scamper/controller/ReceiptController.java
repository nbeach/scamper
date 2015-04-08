package com.nicholasbeach.scamper.controller;

import javax.inject.Inject;

import com.nicholasbeach.scamper.service.ReceiptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nicholasbeach.scamper.domain.Receipt;

import java.util.List;

@RequestMapping(value =  "/receipt")
@RestController
public class ReceiptController extends AbstractRestfulController<Receipt> {

    @Inject
    private ReceiptService receiptService;

    protected ReceiptService getService() {
		return receiptService;
	}
	
	protected Class<Receipt> getResourceClass() {
		return Receipt.class;
	}
	
	@RequestMapping(value = "/{receiptId}/image.**", method = RequestMethod.GET)
	public byte[] getImageFile(@PathVariable Integer receiptId) {
		Receipt receipt = receiptService.retrieve(receiptId);
	
		return receipt.getFile();
	}


    @Override
    public ResponseEntity<Object> retrieveAll() {
        log.info("Get collection requested");
        List<Receipt> results = getService().retrieveAll();

        for (Receipt receipt : results) {
            receipt.setFile(null);
        }

        return new ResponseEntity<Object>(results, HttpStatus.OK);

    }

}
