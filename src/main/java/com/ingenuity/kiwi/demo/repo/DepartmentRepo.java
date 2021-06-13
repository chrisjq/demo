package com.ingenuity.kiwi.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingenuity.kiwi.demo.entities.Department;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

}
