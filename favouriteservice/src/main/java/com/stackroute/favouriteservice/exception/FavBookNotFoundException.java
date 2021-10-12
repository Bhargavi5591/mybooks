package com.stackroute.favouriteservice.exception;

public class FavBookNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public FavBookNotFoundException(String message) {
		super(message);
	}
}