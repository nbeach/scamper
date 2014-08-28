package com.nicholasbeach.scamper.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicholasbeach.scamper.domain.Account;
import com.nicholasbeach.scamper.service.DaoService;

@RequestMapping(value =  "/account")
@RestController
public class AccountController extends DatabaseRestfulController<Account> {
	
	@Resource(name = "AccountServiceImpl")
	private DaoService<Account> accountService;

	protected DaoService<Account> getDaoService() {
		return accountService;
	}
	
	protected Class<Account> getResourceClass() {
		return Account.class;
	}
	
}
