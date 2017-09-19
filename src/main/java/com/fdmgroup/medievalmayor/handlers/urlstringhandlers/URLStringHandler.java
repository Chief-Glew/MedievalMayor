package com.fdmgroup.medievalmayor.handlers.urlstringhandlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;

import com.fdmgroup.medievalmayor.city.City;

public abstract class URLStringHandler {
	
	static final Logger logger = LogManager.getLogger("URLStringHandler.class");

	protected URLStringHandler next;
	
	public void addToChain(URLStringHandler handler) {
		if (next==null) {
			next = handler;
		}
		else {
			next.addToChain(handler); 
		}
		logger.debug("addToChain method used");
	}
	
	public abstract String handle(City city, String urlString, Model model);
}
