package com.mindex.challenge.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.service.exception.ReportingStructureException;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

	private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

	@Autowired
	private EmployeeService employeeService;

	@Override
	public ReportingStructure generateReportingStructure(final String employeeId) throws ReportingStructureException {
		LOG.debug("Generating reportingStructure for employee with employee ID: [{}]", employeeId);

		Employee employee = employeeService.read(employeeId);
		HashMap<String, Boolean> employeeIdMap = new HashMap<>();
		employeeIdMap.put(employeeId, true);
		
		int numberOfReports = getNumberOfReports(employee, employeeIdMap);
		ReportingStructure reportingStructure = new ReportingStructure(employee, numberOfReports);

		return reportingStructure;
	}

	/**
	 * Retrieves the number of reports for the given employee and updates the direct
	 * report of the Employee through object reference.
	 * 
	 * @param employee      The Employee for which the direct reports are to be
	 *                      found.
	 * @param employeeIdMap The Map containing employees whose direct reports are
	 *                      already found.
	 * @return The number of reports.
	 * @throws ReportingStructureException When there is a cyclic reporting.
	 */
	private int getNumberOfReports(Employee employee, HashMap<String, Boolean> employeeIdMap)
			throws ReportingStructureException {
		int numberOfReports = 0;

		List<Employee> directReports = employee.getDirectReports();
		if (directReports != null) {
			for (Employee reportingEmployee : directReports) {

				checkCyclicReporting(employeeIdMap, reportingEmployee);

				Employee reportingEmployeeRetrieved = employeeService.read(reportingEmployee.getEmployeeId());
				updateEmployeeDetails(reportingEmployee, reportingEmployeeRetrieved);

				numberOfReports += 1 + getNumberOfReports(reportingEmployee, employeeIdMap);
			}
		}

		return numberOfReports;
	}

	/**
	 * Checks whether there is a cyclic reporting and throws exception if found.
	 * 
	 * @param employeeIdMap The Map containing employees whose direct reports are
	 *                      already found.
	 * @param employee      The employee for which cyclic reporting is checked.
	 * @throws ReportingStructureException Exception throws when there's a cyclic
	 *                                     reporting.
	 */
	private void checkCyclicReporting(HashMap<String, Boolean> employeeIdMap, Employee employee)
			throws ReportingStructureException {
		if (employeeIdMap.getOrDefault(employee.getEmployeeId(), false)) {
			throw new ReportingStructureException("Cyclic employee dependency found in the reporting structure.");
		} else {
			employeeIdMap.put(employee.getEmployeeId(), true);
		}
	}

	/**
	 * Updates the fields of employee without data from employee with data.
	 * 
	 * @param employeeWithoutData The Employee not containing data.
	 * @param employeeWithData    The Employee containing data.
	 */
	private void updateEmployeeDetails(Employee employeeWithoutData, Employee employeeWithData) {
		employeeWithoutData.setFirstName(employeeWithData.getFirstName());
		employeeWithoutData.setLastName(employeeWithData.getLastName());
		employeeWithoutData.setPosition(employeeWithData.getPosition());
		employeeWithoutData.setDepartment(employeeWithData.getDepartment());
		employeeWithoutData.setDirectReports(employeeWithData.getDirectReports());
	}
}