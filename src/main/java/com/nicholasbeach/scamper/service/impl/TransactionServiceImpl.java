package com.nicholasbeach.scamper.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.nicholasbeach.scamper.domain.Transaction;
import com.nicholasbeach.scamper.service.DatabaseDoaService;
import com.nicholasbeach.scamper.util.rowmapper.TransactionRowMapper;

@Scope("singleton")
@Service("TransactionServiceImpl")
public class TransactionServiceImpl extends DatabaseDoaService<Transaction> {

	@Resource
	private Map<String, String> transactionServiceSql;

	protected RowMapper<Transaction> getRowMapper() {
		return new TransactionRowMapper();
	}

	protected Map<String, String> getSql() {
		return transactionServiceSql;
	}

}
