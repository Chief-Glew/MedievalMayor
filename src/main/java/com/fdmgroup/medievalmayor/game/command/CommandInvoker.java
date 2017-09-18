package com.fdmgroup.medievalmayor.game.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class CommandInvoker {
	
	private static final Logger logger = LogManager.getLogger("CommandInvoker.class");

	private UserCommand command;

	public void setCommand(UserCommand command){
		logger.debug("Command set");
		this.command = command; 
	}
	public void invokeCommands(){
		logger.debug("Command executed");
		command.execute();
	}
	
	
}
