package com.nicholasbeach.scamper.domain;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Account implements DatabaseRow, RestfulResource {
	
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

	@JsonIgnore
	public PreparedStatement getInsertPreparedStatement(PreparedStatement ps) throws SQLException {
		ps.setString(1, getName());
		return ps;
	}
	
	@JsonIgnore
	public PreparedStatement getUpdatePreparedStatement(PreparedStatement ps) throws SQLException {
		ps.setString(1, getName());
		ps.setInt(2, getId());
		return ps;
	}

	
}
