package com.ingenuity.kiwi.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingenuity.kiwi.demo.entities.Department;

/**
 * Default repo for getting departments from the Database.
 * @author chris
 *
 */
public interface DepartmentRepo extends JpaRepository<Department, Long> {

}
