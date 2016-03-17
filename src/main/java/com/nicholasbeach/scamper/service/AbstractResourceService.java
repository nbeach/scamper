package com.nicholasbeach.scamper.service;

import com.nicholasbeach.scamper.domain.RestfulResource;
import com.nicholasbeach.scamper.persistence.ResourceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class AbstractResourceService<T extends RestfulResource> implements ResourceService<T> {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    public T retrieve(Integer id) {
        return getDao().retrieve(id);
    }

    public List<T> retrieveAll() {
        return getDao().retrieveAll();
    }

    public void create(T object) {
        getDao().create(object);
    }

    public void create(List<T> objects) {
        for(T object : objects) {
            getDao().create(object);
        }
    }

    public boolean update(T object) {
        return getDao().update(object);
    }

    public boolean delete(Integer id) {
        return getDao().delete(id);
    }

    abstract protected Class<T> getResourceClass();
    abstract protected ResourceDao<T> getDao();

}
