package com.springboot.rest.dao;

import java.util.List;

import com.springboot.rest.entity.Employee;

public interface EmployeeDao {
	
	public List<Employee> getEmployees();
	
	public Employee getEmployee(int id);
	
	public String saveEmployee(Employee employee);
	
	public String deleteEmployee(int id);
	
	public boolean checkEmailId(String emailId,int eid);
	
}
