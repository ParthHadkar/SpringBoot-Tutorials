package com.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.model.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private List<Employee> employees;
	
	@PostConstruct
	public void loadData() {
		Employee emp1 = new Employee(1, "parth", "hadkar", "parth@gmail.com");
		Employee emp2 = new Employee(2, "nikhil", "etame", "nikhil@gmail.com");
		Employee emp3 = new Employee(3, "deepak", "pejhale", "deepak@gmail.com");
		Employee emp4 = new Employee(4, "omkar", "pednekar", "omkar@gmail.com");
		Employee emp5 = new Employee(5, "nitin", "ghadi", "nitin@gmail.com");
		
		employees = new ArrayList<Employee>();
		employees.add(emp1);
		employees.add(emp2);
		employees.add(emp3);
		employees.add(emp4);
		employees.add(emp5);
	}
	
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		theModel.addAttribute("employees",employees);
		return "list-employee";
	}
}
