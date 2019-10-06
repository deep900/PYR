/**
 * 
 */
package com.pradheep.web.exception;

/**
 * @author pradheep.p
 *
 */
public class UnKnownUserException extends RuntimeException {
	public UnKnownUserException(String errorMessage) {
		super(errorMessage);
	}
}
