package com.mindex.challenge.data;

import java.util.List;

import org.springframework.data.annotation.Id;

import org.springframework.data.annotation.Id;

import org.springframework.data.annotation.Id;

import org.springframework.data.annotation.Id;

import org.springframework.data.annotation.Id;

public class Employee {
	@Id
	private String employeeId;
	private String firstName;
	private String lastName;
	private String position;
	private String department;
	private List<Employee> directReports;

	public Employee() {
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(final String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(final String position) {
		this.position = position;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(final String department) {
		this.department = department;
	}

	public List<Employee> getDirectReports() {
		return directReports;
	}

	public void setDirectReports(final List<Employee> directReports) {
		this.directReports = directReports;
	}
}
