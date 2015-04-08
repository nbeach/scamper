package com.nicholasbeach.scamper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.nicholasbeach.scamper.domain.RestfulResource;
import com.nicholasbeach.scamper.service.AbstractResourceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AbstractRestfulControllerTest {

    @Mock
    AbstractResourceService<RestfulResource> service;

    @Mock
    ObjectMapper jsonMapper;

    AbstractRestfulController<RestfulResource> controller;
    RestfulResource resource;
    List<RestfulResource> resourceList;

    @Before
    public void before() {

        controller = new AbstractRestfulController<RestfulResource>(jsonMapper) {

            @Override
            protected Class<RestfulResource> getResourceClass() {
                return RestfulResource.class;
            }

            @Override
            protected AbstractResourceService<RestfulResource> getService() {
                return service;
            }
        };

        resource = new RestfulResource() {

            @Override
            public Integer getId() {
                return 5;
            }

            @Override
            public void setId(Integer id) {

            }
        };

        resourceList = new ArrayList<RestfulResource>();
        resourceList.add(resource);
        resourceList.add(resource);

    }

    @Test
    public void getCollection() {
        when(service.retrieveAll()).thenReturn(resourceList);

        ResponseEntity<Object> result = controller.retrieveAll();

        verify(service).retrieveAll();
        Assert.assertSame(resourceList, result.getBody());
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getResourceExists() {
        when(service.retrieve(5)).thenReturn(resource);

        ResponseEntity<Object> result = controller.retrieve(5);

        verify(service).retrieve(5);
        Assert.assertSame(resource, result.getBody());
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getResourceDoesNotExist() {
        when(service.retrieve(5)).thenReturn(null);

        ResponseEntity<Object> result = controller.retrieve(5);

        verify(service).retrieve(5);
        Assert.assertEquals(String.class, result.getBody().getClass());
        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void deleteResourceExists() {
        when(service.delete(5)).thenReturn(true);

        ResponseEntity<Object> result = controller.delete(5);

        verify(service).delete(5);
        Assert.assertEquals(null, result.getBody());
        Assert.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void deleteResourceDoesNotExist() {
        when(service.delete(5)).thenReturn(false);

        ResponseEntity<Object> result = controller.delete(5);

        verify(service).delete(5);
        Assert.assertEquals(String.class, result.getBody().getClass());
        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void createResource() throws IOException {
        String json = "{single object}";

        when(jsonMapper.readValue(json, RestfulResource.class)).thenReturn(resource);

        ResponseEntity<Object> result = controller.create(json);

        verify(jsonMapper).readValue(json, RestfulResource.class);
        verify(service).create(Matchers.anyListOf(RestfulResource.class));
        Assert.assertEquals(resource, result.getBody());
        Assert.assertEquals(HttpStatus.CREATED, result.getStatusCode());
   }

    @Test
    public void createCollection() throws IOException {
        String json = "[array of objects] ";

        ObjectMapper mapper = new ObjectMapper();

        when(jsonMapper.getTypeFactory()).thenReturn(mapper.getTypeFactory());
        when(jsonMapper.readValue(Matchers.same(json), Matchers.any(CollectionType.class))).thenReturn(resourceList);

        ResponseEntity<Object> result = controller.create(json);

        verify(jsonMapper).getTypeFactory();
        verify(jsonMapper).readValue(Matchers.same(json), Matchers.any(CollectionType.class));
        verify(service).create(Matchers.anyListOf(RestfulResource.class));

        Assert.assertEquals(resource,  ((List<RestfulResource>) result.getBody()).get(0));
        Assert.assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void updateResourceNotFound() throws IOException {
        String json = "who cares";

        when(jsonMapper.readValue(json, RestfulResource.class)).thenReturn(resource);
        when(service.update(resource)).thenReturn(false);

        ResponseEntity<Object> result = controller.update(5, json);

        verify(jsonMapper).readValue(json, RestfulResource.class);
        verify(service).update(resource);
        Assert.assertEquals(String.class, result.getBody().getClass());
        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}
