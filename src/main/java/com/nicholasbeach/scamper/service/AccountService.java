package com.nicholasbeach.scamper.service;

import com.nicholasbeach.scamper.domain.Account;
import com.nicholasbeach.scamper.persistence.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends AbstractResourceService<Account> {

    @Autowired
    private AccountDao accountDao;

    @Override
    protected Class<Account> getResourceClass() {
        return Account.class;
    }

    @Override
    protected AccountDao getDao() {
        return accountDao;
    }
}
