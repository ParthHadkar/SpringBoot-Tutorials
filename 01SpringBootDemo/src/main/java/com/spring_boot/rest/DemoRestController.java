package com.spring_boot.rest;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {
	
	// define endpoint for "/" - return welcome world with server current time
	@GetMapping("/")
	public String sayHello() {
		return "Hello World !!!! Time on server is "+LocalDateTime.now();
	}
}
