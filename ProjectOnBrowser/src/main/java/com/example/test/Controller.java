package com.example.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/getData")
	public String getData() {
		return "Welcome to Java Helping Hand";
		
	}
}
