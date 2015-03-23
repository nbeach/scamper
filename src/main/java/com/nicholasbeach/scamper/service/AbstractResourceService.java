package com.nicholasbeach.scamper.service;

import com.nicholasbeach.scamper.domain.RestfulResource;
import com.nicholasbeach.scamper.persistence.ResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class AbstractResourceService<T extends RestfulResource> implements ResourceService<T> {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    public T retrieve(Integer id) {
        return getMapper().retrieve(id);
    }

    public List<T> retrieveAll() {
        return getMapper().retrieveAll();
    }

    public List<T> retrieveUpTo(int limit) {
        return getMapper().retrieveUpTo(limit);
    }

    public void create(T object) {
        getMapper().create(object);
    }

    public boolean update(T object) {
        return getMapper().update(object);
    }

    public boolean delete(Integer id) {
        return getMapper().delete(id);
    }

    abstract protected Class<T> getResourceClass();
    abstract protected ResourceMapper<T> getMapper();

}
