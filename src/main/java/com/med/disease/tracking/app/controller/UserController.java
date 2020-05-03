package com.med.disease.tracking.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.med.disease.tracking.app.config.jwt.JwtUtils;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.handler.UserRegistrationHandler;
import com.med.disease.tracking.app.model.User;
import com.med.disease.tracking.app.model.request.LoginRequest;
import com.med.disease.tracking.app.model.response.JwtResponse;
import com.med.disease.tracking.app.repository.IUserRepository;
import com.med.disease.tracking.app.service.impl.UserDetailsImpl;

@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	BeanFactory beanFactory;

	@Autowired
	JwtUtils jwtUtils;
	
	
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
	public List<UserDTO> getUsers(){
		return userRepository.findAll();
	}

	@PostMapping("/user")
	public void insertUser(){
		userRepository.insert(new UserDTO("sunil", "saibol", true));
	}
	
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		logger.info("111111111111111111");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getUid(), 
												 userDetails.getUsername(), 
												 userDetails.getMobile(), 
												 roles));
	}
	
	
	@RequestMapping(path = "/reguser/createUser", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO,BindingResult bindingResult) throws Exception {
		
		return (ResponseEntity<?>) beanFactory.getBean(UserRegistrationHandler.class)
				.handle(userDTO, bindingResult);
		
	}
	
	
	
	
}
