package com.fdmgroup.todolist.todolist;

import com.fdmgroup.todolist.todolist.todoitem.ToDoItem;

public interface ModifyItems {
	public void addItemToList(ToDoItem item);
	public void removeItemFromList(long id);
	public void updateItemInList(ToDoItem item);
}
