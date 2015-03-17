package com.nicholasbeach.scamper.domain;

public class Tag implements RestfulResource {
	
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

}
