package com.ingenuity.kiwi.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingenuity.kiwi.demo.entities.Activity;
import com.ingenuity.kiwi.demo.repo.ActivityRepo;

@RestController
public class ActivityRestController implements ErrorController {
	private final ActivityRepo repo;

	private static final Logger logger = LoggerFactory.getLogger(ActivityRestController.class);

	public ActivityRestController(ActivityRepo repo) {
		this.repo = repo;
	}

	@GetMapping("/api/activity")
	List<Activity> all() {
		logger.info("Getting all");
		return repo.findAll();
	}
}
