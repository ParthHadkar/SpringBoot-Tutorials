package com.spring_boot.rest;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

	// define endpoint for "/" - return Hello world with server current time
	@GetMapping("/")
	public String sayHello() {
		return "Hello World !!!! Time on server is "+LocalDateTime.now();
	}

	// expose endpoint for "/workout" - return daily workout
	@GetMapping("/workout")
	public String getDailyWorkout() {
		return "Do Fifty push-up";
	}

	// expose endpoint for "/fortune" - return daily fortune
	@GetMapping("/fortune")
	public String getDailyFortune() {
		return "Today is your luck day";
	}

}
