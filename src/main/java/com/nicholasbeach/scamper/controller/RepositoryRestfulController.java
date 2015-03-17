package com.nicholasbeach.scamper.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.nicholasbeach.scamper.domain.RestfulResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicholasbeach.scamper.persistence.ResourceMapper;

public abstract class RepositoryRestfulController<T extends RestfulResource> implements RestfulController<T> {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	//Generic responses
	private ResponseEntity<Object> responseNotFound = new ResponseEntity<Object>("Resource not found", HttpStatus.NOT_FOUND);
	private ResponseEntity<Object> responseNoContent = new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getResource(@PathVariable int id) {
		logger.info("Get resource requested. id={}", id);
		
		T resource = getDaoService().retrieve(id);
		
		if(resource != null) {
			return new ResponseEntity<Object>(resource, HttpStatus.OK);
		} else {
			return responseNotFound;
		}
		 
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Object> createResource(@RequestBody String json) {
		logger.info("Create resource requested");
		
		T resource = mapJsonToObject(json);
		
		if(resource != null) {
			resource = getDaoService().create(resource);
		} else {
			return new ResponseEntity<Object>("Error mapping JSON to resource", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		UriComponentsBuilder urib = ServletUriComponentsBuilder.fromCurrentRequest();
		UriComponents uriComponents = urib.path("/{id}").buildAndExpand(resource.getId());
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(uriComponents.toUri());
	    
	    logger.info("Create resource succeeded. id={}", resource.getId());
	    return new ResponseEntity<Object>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateResource(@PathVariable int id, @RequestBody String json) {
		boolean updateResult = false;
		
		T resource = mapJsonToObject(json);
		
		if(resource != null) {
			logger.info("Update resource requested. id={}", id);
			resource.setId(id);
			updateResult = getDaoService().update(resource);
		} else {
			return new ResponseEntity<Object>("Error mapping JSON to resource", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		//If the update succeeded return success
		if(updateResult) {
			return responseNoContent;
			
		} else { //Else it didn't exist so return not found
			return responseNotFound;
		}
		
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteResource(@PathVariable int id) {
		logger.info("Delete resource requested. id={}", id);
		
		boolean deleteResult = getDaoService().delete(id);
		
		//If the delete succeeded return success
		if(deleteResult) {
			return responseNoContent;
			
		} else { //Else it didn't exist so return not found
			return responseNotFound;
		}

	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> getCollection(@RequestParam(value = "limit", required=false) Integer limit) {		
		logger.info("Get collection requested. limit={}", limit);
		List<T> results = null;
		
		//Call the appropriate function based in the limit param value
		if(limit != null) { 	
			results = getDaoService().getLimited(limit);
		} else { 
			results =  getDaoService().retrieveAll();
		}
		
		//If there are results
		if(results != null) {
			return new ResponseEntity<Object>(results, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(new ArrayList<T>(), HttpStatus.OK);
		}

	}
	
	
	private T mapJsonToObject(String json)
	{
		ObjectMapper mapper = new ObjectMapper();
		T resource = null;
		
		try {
			resource = mapper.readValue(json, getResourceClass());
		} catch (JsonParseException exception) {	
			logger.error("An error occurred while parsing JSON for a create. Error = {}", exception.getMessage());
			return null;
			
		} catch (JsonMappingException exception) {
			logger.error("An error occurred while mapping JSON to object for a create. Error = {}", exception.getMessage());
			return null;
			
		} catch (IOException exception) {
			logger.error("An error occurred while proccessing JSON for a create. Error = {}", exception.getMessage());
			return null;
		}
		
		return resource;
	}
	
	abstract protected Class<T> getResourceClass();
	abstract protected ResourceMapper<T> getDaoService();
	
}
