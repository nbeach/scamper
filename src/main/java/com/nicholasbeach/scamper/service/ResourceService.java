package com.nicholasbeach.scamper.service;

import java.util.List;

public interface ResourceService<T> {
    public T retrieve(Integer id);
    public List<T> retrieveAll();
    public List<T> retrieveUpTo(int limit);
    public void create(T object);
    public boolean update(T object);
    public boolean delete(Integer id);
}
