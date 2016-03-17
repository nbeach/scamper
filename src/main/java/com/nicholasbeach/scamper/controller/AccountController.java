package com.nicholasbeach.scamper.controller;

import com.nicholasbeach.scamper.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicholasbeach.scamper.domain.Account;

@RequestMapping(value =  "/account")
@RestController
public class AccountController extends AbstractRestfulController<Account> {

    @Autowired
    private AccountService accountService;

	protected AccountService getService() {
		return accountService;
	}

	protected Class<Account> getResourceClass() {
		return Account.class;
	}

}
