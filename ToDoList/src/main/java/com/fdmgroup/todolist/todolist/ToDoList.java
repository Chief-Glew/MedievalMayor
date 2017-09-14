package com.fdmgroup.todolist.todolist;

import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.todolist.todolist.todoitem.ToDoItem;

public class ToDoList {

	private List<ToDoItem> toDoItems;

	public ToDoList(List<ToDoItem> list) {
		this.toDoItems = list;
	}

	protected void addItemToList(ToDoItem item) {
		toDoItems.add(item);
	}

	protected void removeItemFromList(ToDoItem item) {
		toDoItems.remove(item);
	}

	protected void updateItemInList(ToDoItem item) {
		long id = item.getItemId();
		System.out.println(item);
		ToDoItem matchedItem = new ToDoItem(id, null);
		for (ToDoItem oldItem : toDoItems) {
			if (oldItem.getItemId() == id) {
				matchedItem = oldItem;
			}
		}
		removeItemFromList(matchedItem);
		addItemToList(item);
	}

	protected List<ToDoItem> getAllItemsInList() {
		return toDoItems;
	}
}
