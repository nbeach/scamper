package com.nicholasbeach.scamper.controller;

import javax.inject.Inject;

import com.nicholasbeach.scamper.service.ReceiptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nicholasbeach.scamper.domain.Receipt;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
	
	@RequestMapping(value = "/{receiptId}.**", method = RequestMethod.GET)
	public byte[] getImageFile(@PathVariable Integer receiptId) {
		Receipt receipt = receiptService.retrieve(receiptId);
	
		return receipt.getFileBytes();
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {

		 if (!file.isEmpty()) {
            log.info("File uploaded: {}", file.getOriginalFilename());
        	Receipt receipt;
			try {
				receipt = new Receipt(file.getContentType(), file.getBytes());
				receiptService.create(receipt);

			} catch (IOException e) {
                log.error("Upload failed because of IO exception");
				e.printStackTrace();
                return new ResponseEntity<Object>("Error: IO exception", HttpStatus.INTERNAL_SERVER_ERROR);
			}

        } else {
        	log.error("Upload failed because the file was empty.");
            return new ResponseEntity<Object>("Error: upload file is empty", HttpStatus.INTERNAL_SERVER_ERROR);
        }

		 return new ResponseEntity<Object>("Success", HttpStatus.OK);
	}


    //Changing request mapping so that the file upload method can use this method's default request mapping
    @Override
    @RequestMapping(value = "/null", method = RequestMethod.POST)
    public ResponseEntity<Object> createResource(@RequestBody String json) {
        return null;
    }
}
