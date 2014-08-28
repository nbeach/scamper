package com.nicholasbeach.scamper.service;

import java.util.List;

public interface DaoService<T> {

	public T get(Integer id);
	public List<T> getAll();
	public List<T> getLimited(int limit);
	public T create(T object);
	public boolean update(T object);
	public boolean delete(Integer id);

}
