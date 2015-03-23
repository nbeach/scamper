package com.nicholasbeach.scamper.service;

import com.nicholasbeach.scamper.domain.Account;
import com.nicholasbeach.scamper.persistence.AccountMapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class AccountService extends AbstractResourceService<Account> {

    @Inject
    private AccountMapper accountMapper;

    @Override
    protected Class<Account> getResourceClass() {
        return Account.class;
    }

    @Override
    protected AccountMapper getMapper() {
        return accountMapper;
    }
}
