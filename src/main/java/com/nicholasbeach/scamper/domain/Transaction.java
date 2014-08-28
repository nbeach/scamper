package com.nicholasbeach.scamper.domain;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Transaction implements DatabaseRow, RestfulResource {
	
	private Integer id;
	private Integer accountId;
	private Integer receiptId;
	private Date date;
	private String description;
	private BigDecimal ammount;
	private String note;
	
	
	
	public Transaction() {
		super();
	}

	public Transaction(Date date, String description, BigDecimal ammount) {
		super();
		this.date = date;
		this.description = description;
		this.ammount = ammount;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getReceiptId() {
		return receiptId;
	}
	
	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}
	
	public Integer getAccountId() {
		return accountId;
	}
	
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public BigDecimal getAmmount() {
		return ammount;
	}
	
	public void setAmmount(BigDecimal ammount) {
		this.ammount = ammount;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", receiptId=" + receiptId
				+ ", accountId=" + accountId + ", date=" + date
				+ ", description=" + description + ", ammount=" + ammount
				+ ", note=" + note + "]";
	}
	
	@JsonIgnore
	public PreparedStatement getInsertPreparedStatement(PreparedStatement ps) throws SQLException {
		
		ps.setInt(1, getReceiptId());
		ps.setDate(2, new java.sql.Date(getDate().getTime()));
		ps.setString(3, getDescription());
		ps.setDouble(3, getAmmount().doubleValue());
		ps.setString(4, getNote());
		
		return ps;
	}
	
	@JsonIgnore
	public PreparedStatement getUpdatePreparedStatement(PreparedStatement ps) throws SQLException {
		
		ps.setInt(1, getReceiptId());
		ps.setDate(2, new java.sql.Date(getDate().getTime()));
		ps.setString(3, getDescription());
		ps.setDouble(3, getAmmount().doubleValue());
		ps.setString(4, getNote());
		ps.setInt(5, getId());
		
		return ps;
	}

}
