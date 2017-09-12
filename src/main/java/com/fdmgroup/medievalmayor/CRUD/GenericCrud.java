package com.fdmgroup.medievalmayor.CRUD;

import java.util.Set;

public interface GenericCrud<T> extends GenericRead<T>, GenericWrite<T>{

	public void create(T t);
	public T read(long id);
	public Set<T> readAll();
	public void update(T t);
	public void delete(long id);

}
