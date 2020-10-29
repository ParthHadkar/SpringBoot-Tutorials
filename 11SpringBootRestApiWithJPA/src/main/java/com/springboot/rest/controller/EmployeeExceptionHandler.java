package com.springboot.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.rest.exception.EmployeeErrorResponse;
import com.springboot.rest.exception.EmployeeNotFoundException;

@ControllerAdvice
public class EmployeeExceptionHandler {

	// define  exception handler method - handle EmployeeNotFoundException
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException exc){
		// create EmployeeErrorResponse object
		EmployeeErrorResponse errorResponse = new EmployeeErrorResponse(HttpStatus.NOT_FOUND.value(),
				exc.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<EmployeeErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}

	// define exception handler method - handle Any Exception(Generic Exception)
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleException(Exception exc){
		// create EmployeeErrorResponse object
		EmployeeErrorResponse errorResponse = new EmployeeErrorResponse(HttpStatus.BAD_REQUEST.value(),
				exc.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<EmployeeErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	
}
