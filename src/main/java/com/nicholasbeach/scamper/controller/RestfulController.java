package com.nicholasbeach.scamper.controller;

import org.springframework.http.ResponseEntity;

public interface RestfulController<T> {
	
	public ResponseEntity<Object> retrieve(int id);
	public ResponseEntity<Object> create(String json);
	public ResponseEntity<Object> update(int id, String json);
	public ResponseEntity<Object> delete(int id);
	
	public ResponseEntity<Object> retrieveAll();

}
