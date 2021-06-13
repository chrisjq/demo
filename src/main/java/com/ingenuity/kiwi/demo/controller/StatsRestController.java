package com.ingenuity.kiwi.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ingenuity.kiwi.demo.entities.Department;
import com.ingenuity.kiwi.demo.entities.Statistic;
import com.ingenuity.kiwi.demo.repo.DepartmentRepo;
import com.ingenuity.kiwi.demo.repo.StatisticsRepo;

@RestController
/**
 * Statistics controller.
 * @author chris
 *
 */
public class StatsRestController implements ErrorController {
	private final StatisticsRepo sRepo;

	private static final Logger logger = LoggerFactory.getLogger(StatsRestController.class);

	public StatsRestController(StatisticsRepo sRepo) {
		this.sRepo = sRepo;
	}

	@GetMapping("/api/stats")
	List<Statistic> all() {
		return sRepo.findAll();
	}
	
	/**
	 * Get a departments stats by its ID.
	 * @param deptID
	 * @return
	 */
	@GetMapping("/api/stats/dept/{deptID}")
	List<Statistic> getByDepartment(@PathVariable("deptID") Long deptID) {
		logger.info("Getting all eith Department ID: " + deptID);
		
		return sRepo.getByDepartment_id(deptID);
	}
}
