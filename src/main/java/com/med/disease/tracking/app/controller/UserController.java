package com.med.disease.tracking.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@GetMapping("/")
	public String main() {
		return "static page display - welcome";
	}
	
	@GetMapping("/user")
	public String user() {
		return "welcome user";
	}

	@GetMapping("/admin")
	public String admin() {
		return "welcome admin";
	}
	
}
