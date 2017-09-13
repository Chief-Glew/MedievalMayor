package com.fdmgroup.todolist.cruds;

import java.util.List;

public interface GenericReadCRUD<T> {
	
	public T read(long id);
	public List<T> readAll();
}
