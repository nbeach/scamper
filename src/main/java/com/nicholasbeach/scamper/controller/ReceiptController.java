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
    public ResponseEntity<Object> retrieveAll(@RequestParam(value = "limit", required = false) Integer limit) {
        log.info("Get collection requested. limit={}", limit);
        List<Receipt> results = null;

        //Call the appropriate function based in the limit param value
        if(limit == null) {
            results =  getService().retrieveAll();
        } else if(limit > 0) {
            results = getService().retrieveUpTo(limit);
        } else {
            return new ResponseEntity<Object>("Error: Limit value must be greater than zero", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        for (Receipt receipt : results) {
            receipt.setFile(null);
        }

        return new ResponseEntity<Object>(results, HttpStatus.OK);

    }

}
