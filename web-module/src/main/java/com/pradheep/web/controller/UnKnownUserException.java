package com.pradheep.web.controller;

public class UnKnownUserException extends RuntimeException {

	public UnKnownUserException(String message) {
		super(message);
	}
}
