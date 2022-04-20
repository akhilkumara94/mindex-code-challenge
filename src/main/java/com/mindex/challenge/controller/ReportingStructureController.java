package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.service.exception.ReportingStructureException;

/**
 * Reporting structure REST controller.
 * 
 * @author akhil
 */
@RestController
public class ReportingStructureController {
	private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

	@Autowired
	private ReportingStructureService ReportingStructureService;

	@GetMapping("/reportingStructure/{employeeId}")
	public ReportingStructure generateReportingStructure(@PathVariable String employeeId)
			throws ReportingStructureException {
		LOG.debug("Received request to generate reporting structure for employee ID [{}]", employeeId);

		return ReportingStructureService.generateReportingStructure(employeeId);
	}
}