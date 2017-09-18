package com.fdmgroup.medievalmayor.demos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogTest {
	
	static final Logger logger = LogManager.getLogger("Farm.class");

	public static void main(String[] args) {
	
		logger.trace("Message");
	}

}
