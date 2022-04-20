package com.mindex.challenge.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
	private String createCompensationUrl;
	private String readCompensationUrl;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CompensationService compensationService;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setup() {
		createCompensationUrl = "http://localhost:" + port + "/compensation";
		readCompensationUrl = "http://localhost:" + port + "/compensation/{id}";
	}

	@Test
	public void testCreateReadCompensation() {
		// Test Create
		Employee employee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
		Compensation expectedCompensation = new Compensation();
		expectedCompensation.setEmployee(employee);
		expectedCompensation.setSalary("90000 USD");
		expectedCompensation.setEffectiveDate(Instant.parse("2022-04-20T00:00:00Z"));
		Compensation actualCompensationCreate = restTemplate
				.postForEntity(createCompensationUrl, expectedCompensation, Compensation.class).getBody();

		assertNotNull(actualCompensationCreate);
		assertCompensationObject(expectedCompensation, actualCompensationCreate);

		// Test Read
		Compensation actualCompensationRead = restTemplate
				.getForEntity(readCompensationUrl, Compensation.class, "16a596ae-edd3-4847-99fe-c4518e82c86f")
				.getBody();

		assertNotNull(actualCompensationRead);
		assertCompensationObject(expectedCompensation, actualCompensationRead);
	}

	private void assertCompensationObject(Compensation expectedCompensation, Compensation actualCompensation) {
		assertTrue(expectedCompensation.getEmployee().getEmployeeId()
				.equals(actualCompensation.getEmployee().getEmployeeId()));
		assertTrue(expectedCompensation.getEmployee().getFirstName()
				.equals(actualCompensation.getEmployee().getFirstName()));
		assertTrue(expectedCompensation.getSalary().equals(actualCompensation.getSalary()));
		assertTrue(expectedCompensation.getEffectiveDate().equals(actualCompensation.getEffectiveDate()));
	}
}
