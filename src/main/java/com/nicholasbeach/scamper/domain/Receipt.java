package com.nicholasbeach.scamper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;

public class Receipt implements RestfulResource {

	private Integer id;
	private String mimeType;

	private byte[] file;

	public Receipt() {
		super();
	}

	public Receipt(String mimeType, byte[] file) {
		super();
		this.mimeType = mimeType;
		this.file = file;
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

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "Receipt [id=" + id + ", mimeType=" + mimeType + ", file="
				+ Arrays.toString(file) + "]";
	}

}
