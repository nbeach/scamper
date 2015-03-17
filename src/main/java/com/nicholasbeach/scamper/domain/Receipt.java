package com.nicholasbeach.scamper.domain;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Receipt implements RestfulResource {
	
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
	
}
