package com.fdmgroup.medievalmayor.CRUD;

public interface GenericWrite<T> {

	public void create(T t);
	public void update(T t);
	public void delete(long id);
}
