package com.nicholasbeach.scamper.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction implements RestfulResource {
	
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

}
