package com.nicholasbeach.scamper.util.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.nicholasbeach.scamper.domain.Account;

public class AccountRowMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

		Account newAccount = new Account();
		
		newAccount.setId(resultSet.getInt("account_id"));
		newAccount.setName(resultSet.getString("name"));
		
		return newAccount;
	}

}
