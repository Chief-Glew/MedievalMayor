package com.fdmgroup.medievalmayor.game.command;

public class CommandInvoker {

	private UserCommand command;

	public void setCommand(UserCommand command){
		this.command = command; 
	}
	public void invokeCommands(){
		command.execute();
	}
}
