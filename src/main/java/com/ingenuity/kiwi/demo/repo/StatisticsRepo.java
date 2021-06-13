package com.ingenuity.kiwi.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingenuity.kiwi.demo.entities.Activity;
import com.ingenuity.kiwi.demo.entities.Statistic;

public interface StatisticsRepo extends JpaRepository<Statistic, Long> {

}
