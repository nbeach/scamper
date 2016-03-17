package com.nicholasbeach.scamper.service;

import com.nicholasbeach.scamper.domain.Receipt;
import com.nicholasbeach.scamper.persistence.ReceiptDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService extends AbstractResourceService<Receipt> {

    @Autowired
    private ReceiptDao receiptDao;

    @Override
    protected Class<Receipt> getResourceClass() {
        return Receipt.class;
    }

    @Override
    protected ReceiptDao getDao() {
        return receiptDao;
    }
}
