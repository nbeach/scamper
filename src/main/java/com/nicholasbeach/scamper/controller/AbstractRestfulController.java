package com.nicholasbeach.scamper.controller;

import java.io.IOException;
import java.util.List;

import com.nicholasbeach.scamper.domain.RestfulResource;
import com.nicholasbeach.scamper.service.AbstractResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRestfulController<T extends RestfulResource> implements RestfulController<T> {
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	//Generic responses
	private ResponseEntity<Object> responseNotFound = new ResponseEntity<Object>("Resource not found", HttpStatus.NOT_FOUND);
	private ResponseEntity<Object> responseNoContent = new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

    ObjectMapper jsonMapper;

    public AbstractRestfulController() {
        this.jsonMapper = new ObjectMapper();
    }

    public AbstractRestfulController(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getResource(@PathVariable int id) {
		log.info("Get resource requested. id={}", id);
		
		T resource = getService().retrieve(id);
		
		if(resource != null) {
			return new ResponseEntity<Object>(resource, HttpStatus.OK);
		} else {
			return responseNotFound;
		}
		 
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Object> createResource(@RequestBody String json) {
		log.info("Create resource requested");
		
		T resource = mapJsonToObject(json);
		
		if(resource != null) {
			getService().create(resource);
		} else {
			return new ResponseEntity<Object>("Error mapping JSON to resource", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	    
	    log.info("Create resource succeeded. id={}", resource.getId());
	    return new ResponseEntity<Object>(resource, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateResource(@PathVariable int id, @RequestBody String json) {

		T resource = mapJsonToObject(json);

        boolean updateResult = false;

		if(resource != null) {
			log.info("Update resource requested. id={}", id);
			resource.setId(id);
			updateResult = getService().update(resource);
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
		log.info("Delete resource requested. id={}", id);
		
		boolean deleteResult = getService().delete(id);
		
		//If the delete succeeded return success
		if(deleteResult) {
			return responseNoContent;
			
		} else { //Else it didn't exist so return not found
			return responseNotFound;
		}

	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> getCollection(@RequestParam(value = "limit", required=false) Integer limit) {		
		log.info("Get collection requested. limit={}", limit);
		List<T> results = null;
		
		//Call the appropriate function based in the limit param value
		if(limit == null) {
            results =  getService().retrieveAll();
		} else if(limit > 0) {
            results = getService().retrieveUpTo(limit);
		} else {
            return new ResponseEntity<Object>("Error: Limit value must be greater than zero", HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
	    return new ResponseEntity<Object>(results, HttpStatus.OK);
	}
	
	//TODO: Throw  generic exception rather than returning null
	private T mapJsonToObject(String json)
	{
		T resource = null;

		try {
			resource = jsonMapper.readValue(json, getResourceClass());
		} catch (JsonParseException exception) {	
			log.error("An error occurred while parsing JSON for a create. Error = {}", exception.getMessage());
			return null;
			
		} catch (JsonMappingException exception) {
			log.error("An error occurred while mapping JSON to object for a create. Error = {}", exception.getMessage());
			return null;
			
		} catch (IOException exception) {
			log.error("An error occurred while proccessing JSON for a create. Error = {}", exception.getMessage());
			return null;
		}
		
		return resource;
	}
	
	abstract protected Class<T> getResourceClass();
	abstract protected AbstractResourceService<T> getService();
	
}
