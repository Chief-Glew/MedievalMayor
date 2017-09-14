package com.fdmgroup.todolist.cruds;

public interface GenericWriteCRUD<T> {

	public void create(T t);
	public void update(T t);
	public void remove(T t);
}
