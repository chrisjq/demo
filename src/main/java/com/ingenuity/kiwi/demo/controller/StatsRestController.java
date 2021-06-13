package com.ingenuity.kiwi.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingenuity.kiwi.demo.entities.Department;
import com.ingenuity.kiwi.demo.entities.Statistic;
import com.ingenuity.kiwi.demo.repo.ActivityRepo;
import com.ingenuity.kiwi.demo.repo.StatisticsRepo;

@RestController
public class StatsRestController implements ErrorController{
	private final StatisticsRepo repo;
	
	Logger logger = LoggerFactory.getLogger(StatsRestController.class);
	
	public StatsRestController(StatisticsRepo repo) {
		this.repo = repo;
	}

	@GetMapping("/api/stats")
	List<Statistic> all() {
		return repo.findAll();
	}

}
