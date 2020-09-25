package com.masterCard.connectedCities.exception;

/**
 * Resource not found exception.
 *
 * @author Vijayakumar Gowda
 */
public class ResourceNotFoundException extends Exception {

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}
}
