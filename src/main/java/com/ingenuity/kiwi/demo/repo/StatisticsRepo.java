package com.ingenuity.kiwi.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingenuity.kiwi.demo.entities.Statistic;

/**
 * Default repo for getting statistics from the Database.
 * @author chris
 *
 */
public interface StatisticsRepo extends JpaRepository<Statistic, Long> {
	List<Statistic> getByDepartment_id(Long id);
}
