package com.example.thymeleafpractice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.thymeleafpractice.entity.*;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!
    public List<Employee> findAllByOrderByFirstNameAsc();
	
}
