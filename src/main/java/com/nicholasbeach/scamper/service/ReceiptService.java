package com.nicholasbeach.scamper.service;

import com.nicholasbeach.scamper.domain.Receipt;
import com.nicholasbeach.scamper.persistence.ReceiptMapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class ReceiptService extends AbstractResourceService<Receipt> {

    @Inject
    private ReceiptMapper receiptMapper;

    @Override
    protected Class<Receipt> getResourceClass() {
        return Receipt.class;
    }

    @Override
    protected ReceiptMapper getMapper() {
        return receiptMapper;
    }
}
