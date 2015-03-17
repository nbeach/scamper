package com.nicholasbeach.scamper.domain;

public class Account implements RestfulResource {
	
	private Integer id;
	private String name;
	
	
	public Account() {
		super();
	}

	public Account(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + "]";
	}


}
