package com.nicholasbeach.scamper.controller;

import org.springframework.http.ResponseEntity;

public interface RestfulController<T> {
	
	public ResponseEntity<Object> getResource(int id);
	public ResponseEntity<Object> createResource(String json);
	public ResponseEntity<Object> updateResource(int id, String json);
	public ResponseEntity<Object> deleteResource(int id);
	
	public ResponseEntity<Object> getCollection(Integer limit);	

}
