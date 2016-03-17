package com.nicholasbeach.scamper.service;

import java.util.List;

public interface ResourceService<T> {
    T retrieve(Integer id);
    List<T> retrieveAll();
    void create(T object);
    boolean update(T object);
    boolean delete(Integer id);
}
