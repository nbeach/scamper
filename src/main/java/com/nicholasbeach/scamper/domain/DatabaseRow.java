package com.nicholasbeach.scamper.domain;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface DatabaseRow {
	public Integer getId();
	public void setId(Integer id);

	public PreparedStatement getInsertPreparedStatement(PreparedStatement ps) throws SQLException;
	public PreparedStatement getUpdatePreparedStatement(PreparedStatement ps) throws SQLException;
}
