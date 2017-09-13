package com.fdmgroup.todolist.todolist.todoitem;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="TODOITEM")
public class ToDoItem {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ITEM_ID")
	private long itemId;
	@Column(name="ITEM_NAME")
	private String name;
	@Column(name="ITEM_DUEDATE")
	private LocalDate dueDate;
	@Column(name="ITEM_DESCRIPTION")
	private String description;
	
	public ToDoItem(){}
	
	public ToDoItem(long itemId, String name) {
		//this.itemId = itemId;
		this.name = name;
		this.dueDate = LocalDate.now();
	}
	
	public ToDoItem(long itemId, String name, LocalDate dueDate, String description) {
		this(itemId, name);
		this.dueDate = dueDate;
		this.description = description;
	}

	public long getItemId() {
		return itemId;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ToDoItem [itemId=" + itemId + ", name=" + name + ", dueDate=" + dueDate + ", description=" + description
				+ "]";
	}
}
