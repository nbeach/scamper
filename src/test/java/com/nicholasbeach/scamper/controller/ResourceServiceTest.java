package com.nicholasbeach.scamper.controller;

import com.nicholasbeach.scamper.domain.RestfulResource;
import com.nicholasbeach.scamper.persistence.ResourceDao;
import com.nicholasbeach.scamper.service.AbstractResourceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ResourceServiceTest {

    @Mock
    ResourceDao<RestfulResource> mapper;

    AbstractResourceService<RestfulResource> service;
    RestfulResource resource;
    List<RestfulResource> resourceList;

    @Before
    public void before() {

        service = new AbstractResourceService<RestfulResource>() {

            @Override
            protected Class<RestfulResource> getResourceClass() {
                return RestfulResource.class;
            }

            @Override
            protected ResourceDao<RestfulResource> getDao() {
                return mapper;
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

        resourceList = Arrays.asList(resource, resource);

    }

    @Test
    public void retrieve() {
        when(mapper.retrieve(5)).thenReturn(resource);
        RestfulResource result = service.retrieve(5);
        verify(mapper).retrieve(5);

        assertSame(resource, result);
    }

    @Test
    public void retrieveAll() {
        when(mapper.retrieveAll()).thenReturn(resourceList);
        List<RestfulResource> result = service.retrieveAll();
        verify(mapper).retrieveAll();

        assertSame(resourceList, result);
    }

    @Test
    public void create() {
        service.create(resource);
        verify(mapper).create(resource);
    }

    @Test
    public void createList() {
        service.create(resourceList);
        verify(mapper, times(2)).create(resource);
    }

    @Test
    public void update() {
        when(mapper.update(resource)).thenReturn(true);
        Boolean result = service.update(resource);
        verify(mapper).update(resource);

        assertSame(true, result);
    }

    @Test
    public void delete() {
        when(mapper.delete(5)).thenReturn(true);
        Boolean result = service.delete(5);
        verify(mapper).delete(5);

        assertSame(true, result);
    }

}
