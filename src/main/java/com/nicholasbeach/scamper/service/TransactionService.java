package com.nicholasbeach.scamper.service;

import com.nicholasbeach.scamper.persistence.TransactionDao;
import com.nicholasbeach.scamper.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService extends AbstractResourceService<Transaction> {

    @Autowired
    private TransactionDao transactionDao;

    @Override
    protected Class getResourceClass() {
        return Transaction.class;
    }

    @Override
    protected TransactionDao getDao() {
        return transactionDao;
    }


    public List<Transaction> retrieveInDateRange(Date beginDate, Date endDate) {
        return transactionDao.retrieveInDateRange(beginDate, endDate);
    }

}
