package com.nicholasbeach.scamper.controller;

import javax.inject.Inject;

import com.nicholasbeach.scamper.service.TransactionService;
import org.springframework.web.bind.annotation.*;

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

}
