package com.springboot.rest.service;

import java.util.List;

import com.springboot.rest.entity.Employee;
import com.springboot.rest.model.RestEmployee;

public interface EmployeeService {
	
public List<Employee> getEmployees();
	
	public Employee getEmployee(int id);
	
	public String saveEmployee(Employee employee);
	
	public String deleteEmployee(int id);
	
	public boolean checkEmailId(String emailId,int eid);
	
}
