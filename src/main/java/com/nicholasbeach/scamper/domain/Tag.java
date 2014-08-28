package com.nicholasbeach.scamper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tag implements DatabaseRow, RestfulResource {
	
	private Integer id;
	private String title;
	
	public Tag() {
		super();
	}

	public Tag(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Receipt [id=" + id + ", title=" + title + "]";
	}

	@JsonIgnore
	public PreparedStatement getInsertPreparedStatement(PreparedStatement ps) throws SQLException {
		ps.setString(1, getTitle());
		return ps;
	}
	
	@JsonIgnore
	public PreparedStatement getUpdatePreparedStatement(PreparedStatement ps) throws SQLException {
		ps.setString(1, getTitle());
		ps.setInt(2, getId());
		return ps;
	}
	
}
