package com.springboot.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.entity.Employee;
import com.springboot.rest.exception.EmployeeNotFoundException;
import com.springboot.rest.model.RestEmployee;
import com.springboot.rest.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	// inject dependence for EmployeeService
	@Autowired
	private EmployeeService employeeService;

	// define endpoint for Get "/employees" - return list of employees
	@GetMapping("/employees")
	public List<Employee> getEmployees(){
		List<Employee> employees = employeeService.getEmployees();
		if(employees == null) {
			throw new EmployeeNotFoundException("Employee Not Found.Add Employees!!!!");
		}
		return employees;
	}

	// define endpoint for Get "/employees{employeeId}" - return single employee
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId){
		Employee employee = employeeService.getEmployee(employeeId);
		if(employee == null) {
			throw new EmployeeNotFoundException("Employee Id Not Found : "+employeeId);
		}
		return employee;
	}

	// define endpoint for Post "/employees" - save employee
	@PostMapping("/employees")
	public Employee saveEmployee(@Valid @RequestBody RestEmployee restEmployee,BindingResult theBindingResult){
		if(theBindingResult.hasErrors()) {
			throw new EmployeeNotFoundException(getGlobalAndFieldErrorsMessage(theBindingResult).toString());
		}
		boolean isEmailIdExist = employeeService.checkEmailId(restEmployee.getEmailId(),0);
		if(isEmailIdExist) {
			throw new EmployeeNotFoundException("EmailId Already Exists!!!!");
		}
		Employee employee = new Employee(0,restEmployee.getFirstName(),
				restEmployee.getLastName(),restEmployee.getEmailId());
		String response = employeeService.saveEmployee(employee);
		if(response == null) {
			throw new EmployeeNotFoundException("Error While Saving Employee!!!!");
		}
		return employee;
	}
	
	/*@PostMapping("/employees")
	public String saveEmployee(@Valid @RequestBody RestEmployee restEmployee,BindingResult theBindingResult){
		if(theBindingResult.hasErrors()) {
			throw new EmployeeNotFoundException(getGlobalAndFieldErrorsMessage(theBindingResult).toString());
		}
		boolean isEmailIdExist = employeeService.checkEmailId(restEmployee.getEmailId(),0);
		if(isEmailIdExist) {
			throw new EmployeeNotFoundException("EmailId Already Exists!!!!");
		}
		Employee employee = new Employee(0,restEmployee.getFirstName(),
				restEmployee.getLastName(),restEmployee.getEmailId());
		String response = employeeService.saveEmployee(employee);
		if(response == null) {
			throw new EmployeeNotFoundException("Error While Saving Employee!!!!");
		}
		return response;
	}*/

	// define endpoint for Put "/employees" - update employee
	@PutMapping("/employees")
	public Employee updateEmployee(@Valid @RequestBody RestEmployee restEmployee,BindingResult theBindingResult){
		if(theBindingResult.hasErrors()) {
			throw new EmployeeNotFoundException(getGlobalAndFieldErrorsMessage(theBindingResult).toString());
		}
		Employee checkEmployee = employeeService.getEmployee(restEmployee.getId());
		if(checkEmployee == null) {
			throw new EmployeeNotFoundException("Employee Id Not Found : "+restEmployee.getId());
		}
		boolean isEmailIdExist = employeeService.checkEmailId(restEmployee.getEmailId(),restEmployee.getId());
		if(isEmailIdExist) {
			throw new EmployeeNotFoundException("EmailId Already Exists!!!!");
		}
		Employee employee = new Employee(restEmployee.getId(),restEmployee.getFirstName(),
				restEmployee.getLastName(),restEmployee.getEmailId());
		String response = employeeService.saveEmployee(employee);
		if(response == null) {
			throw new EmployeeNotFoundException("Error While Updating Employee!!!!");
		}
		return employee;
	}
	
	/*@PutMapping("/employees")
	public Map<String,Object> updateEmployee(@Valid @RequestBody RestEmployee restEmployee,BindingResult theBindingResult){
		if(theBindingResult.hasErrors()) {
			throw new EmployeeNotFoundException(getGlobalAndFieldErrorsMessage(theBindingResult).toString());
		}
		Employee checkEmployee = employeeService.getEmployee(restEmployee.getId());
		if(checkEmployee == null) {
			throw new EmployeeNotFoundException("Employee Id Not Found : "+restEmployee.getId());
		}
		boolean isEmailIdExist = employeeService.checkEmailId(restEmployee.getEmailId(),restEmployee.getId());
		if(isEmailIdExist) {
			throw new EmployeeNotFoundException("EmailId Already Exists!!!!");
		}
		Employee employee = new Employee(restEmployee.getId(),restEmployee.getFirstName(),
				restEmployee.getLastName(),restEmployee.getEmailId());
		String response = employeeService.saveEmployee(employee);
		if(response == null) {
			throw new EmployeeNotFoundException("Error While Updating Employee!!!!");
		}
		Map<String,Object> objs = new HashMap<String, Object>();
		objs.put("Employee",employee);
		objs.put("message",response);
		return objs;
	}*/

	// define endpoint for Delete "/employees{employeeId}" - delete employee
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId){
		Employee checkEmployee = employeeService.getEmployee(employeeId);
		if(checkEmployee == null) {
			throw new EmployeeNotFoundException("Employee Id Not Found : "+employeeId);
		}
		String response = employeeService.deleteEmployee(employeeId);
		if(response == null) {
			throw new EmployeeNotFoundException("Error While Deleting Employee!!!!");
		}
		return response;
	}

	public List<String> getGlobalAndFieldErrorsMessage(BindingResult theBindingResult){
		List<String> errorMessage = new ArrayList<String>();
		for(ObjectError objectError : theBindingResult.getGlobalErrors()) {
			errorMessage.add(objectError.getObjectName()+" : "+objectError.getDefaultMessage());
		}
		for(FieldError fieldError : theBindingResult.getFieldErrors()) {
			errorMessage.add(fieldError.getField()+" : "+fieldError.getDefaultMessage());
		}
		return errorMessage;
	}
}
