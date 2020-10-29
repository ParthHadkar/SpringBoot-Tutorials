package com.springboot.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.springboot.rest.validation.EmailValidation;




public class RestEmployee {
	
	// define variable
	private int id;
	
	@NotNull
	@Size(min = 1,message = "First Name Is Required")
	private String firstName;
	
	@NotNull
	@Size(min = 1,message = "Last Name Is Required")
	private String lastName;
	
	@NotNull
	@Pattern(regexp = "\\b[a-zA-z0-9._%+-]{4,32}+@[a-zA-z]{5,32}+\\.[a-zA-z]{2,4}\\b",message = "Email Id Not valid")
	@EmailValidation(value = {"@gmail.com","@yahoo.in","@yahoo.com"},message = "Email Id Must End With @gmail.com OR @yahoo.in/com")
	private String emailId;
	
	// define constructors
	public RestEmployee() {}
	
	public RestEmployee(int id, String firstName, String lastName, String emailId) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}

	// define getters / setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	// define toString

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ "]";
	}
	
}
