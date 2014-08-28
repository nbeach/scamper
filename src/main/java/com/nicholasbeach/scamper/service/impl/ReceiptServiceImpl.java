package com.nicholasbeach.scamper.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.nicholasbeach.scamper.domain.Receipt;
import com.nicholasbeach.scamper.service.DatabaseDoaService;
import com.nicholasbeach.scamper.util.rowmapper.ReceiptRowMapper;

@Scope("singleton")
@Service("ReceiptServiceImpl")
public class ReceiptServiceImpl extends DatabaseDoaService<Receipt> {

	@Resource
	private Map<String, String> receiptServiceSql;

	protected RowMapper<Receipt> getRowMapper() {
		return new ReceiptRowMapper();
	}

	protected Map<String, String> getSql() {
		return receiptServiceSql;
	}

}
