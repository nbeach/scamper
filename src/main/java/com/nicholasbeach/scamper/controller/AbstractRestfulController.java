package com.nicholasbeach.scamper.controller;

import java.util.ArrayList;
import java.util.List;

import com.nicholasbeach.scamper.domain.RestfulResource;
import com.nicholasbeach.scamper.exception.ResourceJsonMappingException;
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
	public ResponseEntity<Object> retrieve(@PathVariable int id) {
		log.info("Get resource requested. id={}", id);
		
		T resource = getService().retrieve(id);
		
		if(resource != null) {
			return new ResponseEntity<Object>(resource, HttpStatus.OK);
		} else {
			return responseNotFound;
		}
		 
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody String json) {
		log.info("Create resource requested");

        List<T> resources;

        try {
            //If the input is an array
            if (json.trim().startsWith("[") && json.trim().endsWith("]")) {
                resources = mapJsonToCollection(json);
            } else {
                resources = new ArrayList<T>();
                resources.add( mapJsonToResource(json));
            }
        } catch(ResourceJsonMappingException exception) {
            return new ResponseEntity<Object>("Error mapping JSON to resource", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        getService().create(resources);

        StringBuilder idListBuilder = new StringBuilder();
        for(T resource : resources) {
            idListBuilder.append(resource.getId()).append(",");
        }

        log.info("Create resource(s) succeeded. id(s)={}", idListBuilder.toString());

        if(resources.size() > 1) {
            return new ResponseEntity<Object>(resources, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Object>(resources.get(0), HttpStatus.CREATED);
        }


	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable int id, @RequestBody String json) {
        T resource;

        try {
            resource = mapJsonToResource(json);
        } catch(ResourceJsonMappingException exception) {
            return new ResponseEntity<Object>("Error mapping JSON to resource", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("Update resource requested. id={}", id);
        resource.setId(id);

        boolean updateSucceeded = false;
        updateSucceeded = getService().update(resource);


		if(updateSucceeded) {
			return responseNoContent;
		} else {
			return responseNotFound;
		}
		
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable int id) {
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
	public ResponseEntity<Object> retrieveAll(@RequestParam(value = "limit", required = false) Integer limit) {
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

	private T mapJsonToResource(String json) throws ResourceJsonMappingException {

		try {
			return jsonMapper.readValue(json, getResourceClass());
		} catch (Exception exception) {
			log.error("An error occurred while mapping JSON to resource. Error = {}", exception.getMessage());
			throw new ResourceJsonMappingException();
		}

	}

    private List<T> mapJsonToCollection(String json) throws ResourceJsonMappingException {

        try {
            return jsonMapper.readValue(json, jsonMapper.getTypeFactory().constructCollectionType(List.class, getResourceClass()));

        } catch (Exception exception) {
            log.error("An error occurred while mapping JSON to resource. Error = {}", exception.getMessage());
            throw new ResourceJsonMappingException();
        }

    }
	
	abstract protected Class<T> getResourceClass();
	abstract protected AbstractResourceService<T> getService();
	
}
