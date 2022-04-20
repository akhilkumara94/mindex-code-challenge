package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.exception.CompensationException;

@Service
public class CompensationServiceImpl implements CompensationService {
	private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private CompensationRepository compensationRepository;

	@Override
	public Compensation createCompensation(Compensation compensation) {
		LOG.debug("Creating compensation for employee with employee ID: [{}]",
				compensation.getEmployee().getEmployeeId());

		Employee employee = employeeService.read(compensation.getEmployee().getEmployeeId());
		compensation.setEmployee(employee);

		return compensationRepository.save(compensation);
	}

	@Override
	public Compensation readCompensation(final String employeeId) throws CompensationException {
		LOG.debug("Reading compensation for employee with employee ID: [{}]", employeeId);

		Employee employee = employeeService.read(employeeId);
		Compensation compensation = compensationRepository.findFirstCompensationByEmployee(employee);

		if (compensation == null) {
			throw new CompensationException("Compensation not found for employee with employee ID: " + employeeId);
		}

		return compensation;
	}
}
