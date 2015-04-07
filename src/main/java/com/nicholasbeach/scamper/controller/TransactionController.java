package com.nicholasbeach.scamper.controller;

import javax.inject.Inject;

import com.nicholasbeach.scamper.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nicholasbeach.scamper.domain.Transaction;

import java.util.Date;

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

    @RequestMapping(value = "", params={"beginDate", "endDate"}, method = RequestMethod.GET)
    public ResponseEntity<Object> retrieveAll(@RequestParam(value = "beginDate", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date beginDate,
                                              @RequestParam(value = "endDate", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {

        return new ResponseEntity<Object>(transactionService.retrieveInDateRange(beginDate, endDate), HttpStatus.OK);

    }



}
