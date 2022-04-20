package com.mindex.challenge.service.exception;

/**
 * Exception class for reporting structure services.
 * 
 * @author akhil
 */
public class ReportingStructureException extends Exception {
	private static final long serialVersionUID = 1L;

	public ReportingStructureException(final String errorMessage) {
		super(errorMessage);
	}
}
