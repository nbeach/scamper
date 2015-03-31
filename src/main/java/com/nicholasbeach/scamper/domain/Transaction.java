package com.nicholasbeach.scamper.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction implements RestfulResource {
	
	private Integer id;
	private Integer accountId;
	private Integer receiptId;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="EST")
    private Date date;
	private String description;
	private BigDecimal amount;
	private String note;
	
	
	
	public Transaction() {
		super();
	}

	public Transaction(Date date, String description, BigDecimal amount) {
		super();
		this.date = date;
		this.description = description;
		this.amount = amount;
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
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
				+ ", description=" + description + ", amount=" + amount
				+ ", note=" + note + "]";
	}

}
