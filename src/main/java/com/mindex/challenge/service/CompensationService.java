package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.exception.CompensationException;

/**
 * Interface for functionality of compensation service.
 * 
 * @author akhil
 */
public interface CompensationService {
	/**
	 * Adds compensation to the passed in employee with the effective date.
	 * 
	 * @param compensation The compensation Object containing employee id,
	 *                     compensation and effective date.
	 * @return The created compensation.
	 */
	Compensation createCompensation(Compensation compensation);

	/**
	 * Retrieves the compensation details of an employee by ID.
	 * 
	 * @param employeeId The employee ID for which the compensation details are
	 *                   retrieved.
	 * @return The {@link Compensation}.
	 */
	Compensation readCompensation(String employeeId) throws CompensationException;
}