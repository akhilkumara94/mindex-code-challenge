package com.mindex.challenge.service.exception;

/**
 * Exception class for compensation services.
 * @author akhil
 */
public class CompensationException extends Exception {
	private static final long serialVersionUID = 1L;

	public CompensationException(final String errorMessage) {
		super(errorMessage);
	}
}
