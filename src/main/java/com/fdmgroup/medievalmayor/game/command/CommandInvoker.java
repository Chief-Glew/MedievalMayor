package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandInvoker {
	
	private static final Logger logger = LogManager.getLogger("CommandInvoker");

	private UserCommand command;

	public void setCommand(UserCommand command){
		logger.trace("Command set");
		this.command = command; 
	}
	public void invokeCommands(){
		logger.trace("Command executed");
		command.execute();
	}
	
	
}
