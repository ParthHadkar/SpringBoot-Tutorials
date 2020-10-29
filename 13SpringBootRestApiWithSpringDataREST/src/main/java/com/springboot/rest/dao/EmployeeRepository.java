package com.springboot.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.springboot.rest.entity.Employee;

@RepositoryRestResource(path = "members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	// that it .... no need to write any code
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details
	
	public Employee findByEmailId(String emailId);
	public Employee findByEmailIdAndIdNot(String emailId,int id);
	
	/**
	 * http://localhost:8080/magic-api/members/?page=1&size=2
	 * http://localhost:8080/magic-api/members/?sort=lastName
	 * http://localhost:8080/magic-api/members/?sort=lastName,firstname,asc
	 * http://localhost:8080/magic-api/members/
	 * */
}
