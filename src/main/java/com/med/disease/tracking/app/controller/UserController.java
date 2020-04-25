package com.med.disease.tracking.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.med.disease.tracking.app.model.User;
import com.med.disease.tracking.app.repository.IUserRepository;

@RestController
public class UserController {
	
	@Autowired
	private IUserRepository userRepository;
	
	@GetMapping("/")
	public String main() {
		return "static page display - welcome";
	}
	
	@GetMapping("/user/{userId}")
	public String user() {
		return "welcome user";
	}

	@GetMapping("/admin")
	public String admin() {
		return "welcome admin";
	}
	
	@GetMapping("/user")
	public List<User> getUsers(){
		return userRepository.findAll();
	}

	@PostMapping("/user")
	public void insertUser(){
		userRepository.insert(new User("sunil", "saibol", true));
	}
}
