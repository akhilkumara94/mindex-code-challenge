package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {
	private String reportingStructureUrl;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ReportingStructureService resportStructureService;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setup() {
		reportingStructureUrl = "http://localhost:" + port + "/reportingStructure/{id}";
	}

	@Test
	public void testCreateReadUpdate() {
		Employee employee = employeeRepository.findByEmployeeId("03aa1462-ffa9-4978-901b-7c001562cf6f");
		for (Employee emp : employee.getDirectReports()) {
			Employee retrievedEmployee = employeeRepository.findByEmployeeId(emp.getEmployeeId());
			emp.setFirstName(retrievedEmployee.getFirstName());
			emp.setLastName(retrievedEmployee.getLastName());
			emp.setDepartment(retrievedEmployee.getDepartment());
			emp.setPosition(retrievedEmployee.getPosition());
			emp.setDirectReports(retrievedEmployee.getDirectReports());
		}
		ReportingStructure expectedReportingStructure = new ReportingStructure(employee, 2);

		ReportingStructure actualReportingStructure = restTemplate
				.getForEntity(reportingStructureUrl, ReportingStructure.class, employee.getEmployeeId()).getBody();
		assertNotNull(actualReportingStructure);
		assertReportingStructureEquals(expectedReportingStructure, actualReportingStructure);
	}

	private void assertReportingStructureEquals(ReportingStructure expectedStructure,
			ReportingStructure actualStructure) {
		assertEquals(expectedStructure.getNumberOfReports(), actualStructure.getNumberOfReports());

		// asserting primary employee
		Employee expectedPrimaryEmployee = expectedStructure.getEmployee();
		Employee actualPrimaryEmployee = actualStructure.getEmployee();
		assertEquals(expectedPrimaryEmployee.getEmployeeId(), actualPrimaryEmployee.getEmployeeId());
		assertEquals(expectedPrimaryEmployee.getFirstName(), actualPrimaryEmployee.getFirstName());
		assertEquals(expectedPrimaryEmployee.getDirectReports().size(),
				actualPrimaryEmployee.getDirectReports().size());

		// asserting 1st direct report
		Employee expectedFirstReport = expectedStructure.getEmployee().getDirectReports().get(0);
		Employee actualFirstReport = actualStructure.getEmployee().getDirectReports().get(0);
		assertEquals(expectedFirstReport.getEmployeeId(), actualFirstReport.getEmployeeId());
		assertEquals(expectedFirstReport.getFirstName(), actualFirstReport.getFirstName());
		assertNull(expectedFirstReport.getDirectReports());
		assertNull(actualFirstReport.getDirectReports());

		// asserting 2nd direct report
		Employee expectedSecondReport = expectedStructure.getEmployee().getDirectReports().get(1);
		Employee actualSecondReport = actualStructure.getEmployee().getDirectReports().get(1);
		assertEquals(expectedSecondReport.getEmployeeId(), actualSecondReport.getEmployeeId());
		assertEquals(expectedSecondReport.getFirstName(), actualSecondReport.getFirstName());
		assertNull(expectedSecondReport.getDirectReports());
		assertNull(actualSecondReport.getDirectReports());
	}
}
