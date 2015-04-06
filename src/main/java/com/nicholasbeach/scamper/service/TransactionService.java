package com.nicholasbeach.scamper.service;

import com.nicholasbeach.scamper.persistence.TransactionMapper;
import com.nicholasbeach.scamper.domain.Transaction;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class TransactionService extends AbstractResourceService<Transaction> {

    @Inject
    private TransactionMapper transactionMapper;

    @Override
    protected Class getResourceClass() {
        return Transaction.class;
    }

    @Override
    protected TransactionMapper getMapper() {
        return transactionMapper;
    }

}
