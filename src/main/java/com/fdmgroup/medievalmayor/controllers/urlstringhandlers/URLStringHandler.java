package com.fdmgroup.medievalmayor.controllers.urlstringhandlers;

import org.springframework.ui.Model;

import com.fdmgroup.medievalmayor.game.city.City;

public abstract class URLStringHandler {

	protected URLStringHandler next;
	
	public void addToChain(URLStringHandler handler) {
		if (next==null) {
			next = handler;
		}
		else {
			next.addToChain(handler);
		}
	}
	
	public abstract String handel(City city, String urlString, Model model);
}
