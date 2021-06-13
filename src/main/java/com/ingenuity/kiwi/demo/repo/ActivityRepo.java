package com.ingenuity.kiwi.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingenuity.kiwi.demo.entities.Activity;

public interface ActivityRepo extends JpaRepository<Activity, Long> {

}
