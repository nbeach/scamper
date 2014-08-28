package com.nicholasbeach.scamper.util.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.nicholasbeach.scamper.domain.Transaction;

public class TransactionRowMapper implements RowMapper<Transaction> {

	@Override
	public Transaction mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		
		Transaction newTransaction = new Transaction();

		newTransaction.setId(resultSet.getInt("transaction_id"));
		newTransaction.setAccountId(resultSet.getInt("account_id"));
		newTransaction.setReceiptId(resultSet.getInt("receipt_id"));
		newTransaction.setDate(resultSet.getDate("date"));
		newTransaction.setDescription(resultSet.getString("description"));
		newTransaction.setAmmount(resultSet.getBigDecimal("ammount"));
		newTransaction.setNote(resultSet.getString("note"));
		
		return newTransaction;
	}

}
