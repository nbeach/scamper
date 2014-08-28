package com.nicholasbeach.scamper.util.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.nicholasbeach.scamper.domain.Receipt;

public class ReceiptRowMapper implements RowMapper<Receipt> {

	@Override
	public Receipt mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

		Receipt newReceipt = new Receipt();
		
		newReceipt.setId(resultSet.getInt("receipt_id"));
		newReceipt.setMimeType(resultSet.getString("mime_type"));
		newReceipt.setFileBytes(resultSet.getBytes("file"));
		
		return newReceipt;
	}

}
