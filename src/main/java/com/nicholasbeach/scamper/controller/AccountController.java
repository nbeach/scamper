package com.nicholasbeach.scamper.controller;

import javax.inject.Inject;

import com.nicholasbeach.scamper.persistence.AccountMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicholasbeach.scamper.domain.Account;
import com.nicholasbeach.scamper.persistence.ResourceMapper;

@RequestMapping(value =  "/account")
@RestController
public class AccountController extends RepositoryRestfulController<Account> {
	
    @Inject
    private AccountMapper accountRepository;

	protected ResourceMapper<Account> getMapper() {
		return accountRepository;
	}
	
	protected Class<Account> getResourceClass() {
		return Account.class;
	}
	
}
