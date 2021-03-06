package com.ingenuity.kiwi.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingenuity.kiwi.demo.entities.Department;
import com.ingenuity.kiwi.demo.repo.DepartmentRepo;

@RestController
/**
 * Controller for Departments.
 * @author chris
 *
 */
public class DepartmentRestController implements ErrorController {
	private final DepartmentRepo repo;

	private static final Logger logger = LoggerFactory.getLogger(DepartmentRestController.class);

	public DepartmentRestController(DepartmentRepo repo) {
		this.repo = repo;
	}

	@GetMapping("/api/department")
	List<Department> all() {
		logger.info("Getting all");
		return repo.findAll();
	}
}
