package com.fdmgroup.todolist.todolist.todoitem;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.fdmgroup.todolist.cruds.GenericReadCRUD;
import com.fdmgroup.todolist.cruds.GenericWriteCRUD;

public class ToDoItemsJPACrud implements GenericWriteCRUD<ToDoItem>, GenericReadCRUD<ToDoItem>{

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public void connect(){
		entityManagerFactory = Persistence.createEntityManagerFactory("toDoList");
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
	}
	
	public void disconnect(){
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Override
	public void create(ToDoItem item) {
		connect();
		entityManager.persist(item);
		entityManager.flush();
		entityManager.getTransaction().commit();
		disconnect();
	}

	@Override
	public void update(ToDoItem item) {
		connect();
		entityManager.find(ToDoItem.class, item.getItemId());
		entityManager.merge(item);
		disconnect();
	}

	@Override
	public void remove(ToDoItem item) {
		connect();
		ToDoItem foo = entityManager.find(ToDoItem.class, item.getItemId());
		System.out.println(item);
		System.out.println(foo);
		entityManager.remove(foo);
		entityManager.flush();
		entityManager.getTransaction().commit();
		disconnect();
	}

	@Override
	public ToDoItem read(long id) {
		connect();
		ToDoItem toDoItem = entityManager.find(ToDoItem.class, id);
		disconnect();
		return toDoItem;
	}

	@Override
	public List<ToDoItem> readAll() {
		connect();
		List<ToDoItem> items;
		TypedQuery<ToDoItem> typedQuery = entityManager.createQuery("SELECT t FROM TODOITEM t", ToDoItem.class);
		items = typedQuery.getResultList();
		System.out.println("CRUD - "+items);
		disconnect();
		return items;
	}
	
}
