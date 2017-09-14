package com.fdmgroup.todolist.todolist;

import java.time.LocalDate;
import java.util.List;

import com.fdmgroup.todolist.todolist.todoitem.ToDoItem;

public interface DisplayItems {
	public List<ToDoItem> showAllItemsInList();
	public ToDoItem getItem(long itemId);
	public List<ToDoItem> showAllItemsOnDate(LocalDate date);
}
