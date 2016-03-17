package com.nicholasbeach.scamper.persistence;

import java.util.List;

public interface ResourceDao<T> {

	T retrieve(Integer id);
	List<T> retrieveAll();
	void create(T object);
	boolean update(T object);
	boolean delete(Integer id);

}
