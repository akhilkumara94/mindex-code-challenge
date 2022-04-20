package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.exception.CompensationException;

/**
 * Compensation REST controller.
 * 
 * @author akhil
 */
@RestController
public class CompensationController {
	private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

	@Autowired
	private CompensationService compensationService;

	@PostMapping("/compensation")
	public Compensation createCompensation(@RequestBody Compensation compensation) {
		LOG.debug("Received compensation create request for employee ID [{}]",
				compensation.getEmployee().getEmployeeId());

		return compensationService.createCompensation(compensation);
	}

	@GetMapping("/compensation/{id}")
	public Compensation readCompensation(@PathVariable String id) throws CompensationException {
		LOG.debug("Received compensation read request for employee ID [{}]", id);

		return compensationService.readCompensation(id);
	}
}