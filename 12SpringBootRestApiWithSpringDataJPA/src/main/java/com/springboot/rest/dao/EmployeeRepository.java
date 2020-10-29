package com.springboot.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	// that it .... no need to write any code
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details
	
	public Employee findByEmailId(String emailId);
	public Employee findByEmailIdAndIdNot(String emailId,int id);
}
