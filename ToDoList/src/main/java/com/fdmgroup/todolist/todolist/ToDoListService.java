package com.fdmgroup.todolist.todolist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.fdmgroup.todolist.cruds.GenericReadCRUD;
import com.fdmgroup.todolist.cruds.GenericWriteCRUD;
import com.fdmgroup.todolist.todolist.todoitem.ToDoItem;
import com.fdmgroup.todolist.todolist.todoitem.ToDoItemsJPACrud;

public class ToDoListService implements DisplayItems, ModifyItems{
	
	private static ToDoListService instance;
	private ToDoList toDoList;
	private GenericReadCRUD<ToDoItem> genericReadCRUD;
	private GenericWriteCRUD<ToDoItem> genericWriteCRUD;
	
	private ToDoListService(List<ToDoItem> list, GenericReadCRUD<ToDoItem> genericReadCRUD,GenericWriteCRUD<ToDoItem> genericWriteCRUD) {
		this.toDoList = new ToDoList(list);
		this.genericReadCRUD = genericReadCRUD;
		this.genericWriteCRUD = genericWriteCRUD;
		
	}

	public static ToDoListService getInstance(){
		if(instance == null){
			ToDoItemsJPACrud toDoItemsJPACrud = new ToDoItemsJPACrud();
			instance = new ToDoListService(
					toDoItemsJPACrud.readAll(),
					toDoItemsJPACrud,
					toDoItemsJPACrud);
		}
		return instance; 
	}
	
	@Override
	public void addItemToList(ToDoItem item) {
		toDoList.addItemToList(item);
		genericWriteCRUD.create(item);
	}

	@Override
	public void removeItemFromList(long id) {
		ToDoItem itemToRemove = getItem(id);
		toDoList.removeItemFromList(itemToRemove);
		genericWriteCRUD.remove(itemToRemove);

	}

	@Override
	public void updateItemInList(ToDoItem item) {
		toDoList.updateItemInList(item);
		genericWriteCRUD.update(item);
	}

	@Override
	public List<ToDoItem> showAllItemsInList() {
		return toDoList.getAllItemsInList();
	}

	@Override
	public ToDoItem getItem(long itemId) {
		for(ToDoItem item : toDoList.getAllItemsInList()){
			if(item.getItemId() == itemId){
				return item;
			}
		}
		return new ToDoItem(itemId,"");
	}

	@Override
	public List<ToDoItem> showAllItemsOnDate(LocalDate date) {
		List<ToDoItem> itemsOnDate = new ArrayList<ToDoItem>();
		for(ToDoItem item : toDoList.getAllItemsInList()){
			if(item.getDueDate() .equals(date)){
				itemsOnDate.add(item);
			}
		}
		return itemsOnDate;
	}

}
