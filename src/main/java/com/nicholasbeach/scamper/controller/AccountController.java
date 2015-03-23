package com.nicholasbeach.scamper.controller;

import javax.inject.Inject;

import com.nicholasbeach.scamper.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicholasbeach.scamper.domain.Account;

@RequestMapping(value =  "/account")
@RestController
public class AccountController extends AbstractRestfulController<Account> {

    @Inject
    private AccountService accountService;

	protected AccountService getService() { return accountService;
	}

	protected Class<Account> getResourceClass() {
		return Account.class;
	}

}
