package com.nicholasbeach.scamper.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.nicholasbeach.scamper.domain.Account;
import com.nicholasbeach.scamper.service.DatabaseDoaService;
import com.nicholasbeach.scamper.util.rowmapper.AccountRowMapper;

@Scope("singleton")
@Service("AccountServiceImpl")
public class AccountServiceImpl extends DatabaseDoaService<Account> {

	@Resource
	private Map<String, String> accountServiceSql;

	protected  RowMapper<Account> getRowMapper() {
		return new AccountRowMapper();
	}

	protected Map<String, String> getSql() {
		return accountServiceSql;
	}

}
