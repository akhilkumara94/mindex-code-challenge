package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.exception.ReportingStructureException;

/**
 * Interface for functionality of reporting structure service.
 * 
 * @author akhil
 */
public interface ReportingStructureService {
	/**
	 * Generates the reporting structure for the employee ID.
	 * 
	 * @param employeeId The employee ID for which reporting structure is generated.
	 * @return The created {@link ReportingStructure}.
	 */
	ReportingStructure generateReportingStructure(String employeeId) throws ReportingStructureException;
}