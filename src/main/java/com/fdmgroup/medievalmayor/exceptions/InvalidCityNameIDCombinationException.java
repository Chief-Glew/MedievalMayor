package com.fdmgroup.medievalmayor.exceptions;

public class InvalidCityNameIDCombinationException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public InvalidCityNameIDCombinationException(String exceptionString) {

		super(exceptionString);
	}

}
