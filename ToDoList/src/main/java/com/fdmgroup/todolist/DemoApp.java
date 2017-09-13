package com.fdmgroup.todolist;

import java.time.LocalDate;

import com.fdmgroup.todolist.todolist.ToDoListService;
import com.fdmgroup.todolist.todolist.todoitem.ToDoItem;

public class DemoApp {

	public static void main(String[] args) {
		ToDoListService toDoListService = ToDoListService.getInstance();
		
		ToDoItem item = new ToDoItem(1l, "make another item");
		ToDoItem item2 = new ToDoItem(2l, "learn java");
		ToDoItem item3 = new ToDoItem(3l, "have lunch");
		ToDoItem item4 = new ToDoItem(4l, "git gud at git");
		
		toDoListService.addItemToList(new ToDoItem(1l, "make another item"));
		toDoListService.addItemToList(new ToDoItem(2l, "learn java"));
		toDoListService.addItemToList(new ToDoItem(3l, "have lunch"));
		toDoListService.addItemToList(new ToDoItem(4l, "git gud at git"));
		
		System.out.println(toDoListService.showAllItemsInList());
		
		//toDoListService.removeItemFromList(item3);
		System.out.println(toDoListService.showAllItemsInList());
		
		//item2.setDescription("Actually make it COBOL");
		toDoListService.updateItemInList(new ToDoItem(2l, "learn COBOL", LocalDate.of(2017, 9, 5), ""));
		System.out.println(toDoListService.showAllItemsInList());
		
		System.out.println(toDoListService.showAllItemsOnDate(LocalDate.of(2017, 9, 5)));
	}

}
