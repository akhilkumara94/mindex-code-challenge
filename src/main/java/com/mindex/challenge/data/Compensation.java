package com.mindex.challenge.data;

import java.time.Instant;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Compensation Entity class.
 * 
 * @author akhil
 */
public class Compensation {
	@DBRef(lazy = true)
	private Employee employee;
	private String salary;
	private Instant effectiveDate;

	/**
	 * Gets the employee associated to the compensation.
	 * 
	 * @return The {@link Employee}.
	 */
	public Employee getEmployee() {
		return this.employee;
	}

	/**
	 * Sets the employee for the compensation.
	 * 
	 * @param employee The employee to be set for the compensation.
	 */
	public void setEmployee(final Employee employee) {
		this.employee = employee;
	}

	/**
	 * Gets salary for the employee.
	 * 
	 * @return The salary as string.
	 */
	public String getSalary() {
		return this.salary;
	}

	/**
	 * Sets the salary for the employee.
	 * 
	 * @param salary The salary to be set for the employee.
	 */
	public void setSalary(final String salary) {
		this.salary = salary;
	}

	/**
	 * Gets the effective date of the compensation.
	 * 
	 * @return The {@link LocalDate} of the compensation.
	 */
	public Instant getEffectiveDate() {
		return this.effectiveDate;
	}

	/**
	 * Sets the effective date for the compensation.
	 * 
	 * @param effectiveDate The date to be set.
	 */
	public void setEffectiveDate(final Instant effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}