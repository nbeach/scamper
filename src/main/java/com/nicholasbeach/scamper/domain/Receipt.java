package com.nicholasbeach.scamper.domain;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Receipt implements DatabaseRow, RestfulResource {
	
	private Integer id;
	private String mimeType;
	
	@JsonIgnore
	private byte[] fileBytes;
	
	public Receipt() {
		super();
	}

	public Receipt(String mimeType, byte[] fileBytes) {
		super();
		this.mimeType = mimeType;
		this.fileBytes = fileBytes;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMimeType() {
		return mimeType;
	}
	
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	@JsonIgnore
	public byte[] getFileBytes() {
		return fileBytes;
	}
	
	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}
	
	@Override
	public String toString() {
		return "Receipt [id=" + id + ", mimeType=" + mimeType + ", fileBytes="
				+ Arrays.toString(fileBytes) + "]";
	}
	
	@JsonIgnore
	public PreparedStatement getInsertPreparedStatement(PreparedStatement ps) throws SQLException {
		ps.setString(1, getMimeType());
		ps.setBytes(2, getFileBytes());
		return ps;
	}
	
	@JsonIgnore
	public PreparedStatement getUpdatePreparedStatement(PreparedStatement ps) throws SQLException {
		ps.setString(1, getMimeType());
		ps.setBytes(2, getFileBytes());
		ps.setInt(3, getId());
		return ps;
	}
	
}
