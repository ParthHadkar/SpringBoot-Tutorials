package com.springboot.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.rest.dao.EmployeeRepository;
import com.springboot.rest.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// define variable for EmployeeDao
	private EmployeeRepository employeeRepository;
	
	// inject dependency using constructor injection
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	
	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployee(int id) {
		// https://community.oracle.com/tech/developers/discussion/4418055/optionals-patterns-and-good-practices
		Optional<Employee> findById = employeeRepository.findById(id);
		Employee employee = null;
		if(findById.isPresent()) {
			employee = findById.get();
		}
		return employee;
	}

	@Override
	public String saveEmployee(Employee employee) {	
		int eId = employee.getId();
		String response = (eId!=0)?"Employee updated successfully":"Employee saved successfully";
		employeeRepository.save(employee);
		return response;
	}

	@Override
	public String deleteEmployee(int id) {
		String response = "Employee deleted successfully";
		employeeRepository.deleteById(id);
		return response;
	}

	@Override
	public boolean checkEmailId(String emailId,int eid) {
		boolean checkEmailPresent = false;
		Employee employee = null;
		if(eid != 0) {
			employee = employeeRepository.findByEmailIdAndIdNot(emailId, eid);
			//System.out.println(eid+ "eid != 0 employee "+employee);
		}
		else {
			employee = employeeRepository.findByEmailId(emailId);
			//System.out.println(eid+ "eid == 0 employee "+employee);
		}
		checkEmailPresent = (employee!=null)?true:false;
		return checkEmailPresent;
	}

}
