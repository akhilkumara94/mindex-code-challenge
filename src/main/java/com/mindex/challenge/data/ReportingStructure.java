package com.mindex.challenge.data;

import java.util.Objects;

/**
 * Reporting Structure Entity class.
 * 
 * @author akhil
 */
public class ReportingStructure {
	private Employee employee;
	private int numberOfReports;

	public ReportingStructure(final Employee employee, final int numberOfReports) {
		this.employee = employee;
		this.numberOfReports = numberOfReports;
	}

	/**
	 * Gets the employee in the reporting structure.
	 * 
	 * @return The {@link Employee}.
	 */
	public Employee getEmployee() {
		return this.employee;
	}

	/**
	 * Sets the employee with the reporting structure.
	 * 
	 * @param employee The employee to be set.
	 */
	public void setEmployee(final Employee employee) {
		this.employee = employee;
	}

	/**
	 * Gets the number of reports to the employee.
	 * 
	 * @return The integer representing the number of reports.
	 */
	public int getNumberOfReports() {
		return this.numberOfReports;
	}

	/**
	 * Sets the number of reports for the employee.
	 * 
	 * @param numberOfReports The number of reports to be set.
	 */
	public void setNumberOfReports(final int numberOfReports) {
		this.numberOfReports = numberOfReports;
	}
}