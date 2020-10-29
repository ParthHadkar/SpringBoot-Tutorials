package com.springboot.rest.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.rest.dao.EmployeeDao;
import com.springboot.rest.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// define variable for EmployeeDao
	private EmployeeDao employeeDao;
	
	// inject dependency using constructor injection
	@Autowired
	public EmployeeServiceImpl(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	
	@Override
	@Transactional
	public List<Employee> getEmployees() {
		return employeeDao.getEmployees();
	}

	@Override
	@Transactional
	public Employee getEmployee(int id) {
		return employeeDao.getEmployee(id);
	}

	@Override
	@Transactional
	public String saveEmployee(Employee employee) {		
		return employeeDao.saveEmployee(employee);
	}

	@Override
	@Transactional
	public String deleteEmployee(int id) {
		return employeeDao.deleteEmployee(id);
	}

	@Override
	@Transactional
	public boolean checkEmailId(String emailId,int eid) {
		return employeeDao.checkEmailId(emailId,eid);
	}

}
