package com.fdmgroup.medievalmayor.CRUD;

import java.util.Set;

public interface GenericRead<T> {
	
	public T read(long id);
	public Set<T> readAll();
}
