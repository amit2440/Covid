package com.med.disease.tracking.app.controller;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.med.disease.tracking.app.config.jwt.JwtUtils;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.handler.UserInfoHandler;
import com.med.disease.tracking.app.handler.UserRegistrationHandler;
import com.med.disease.tracking.app.model.request.LoginRequest;
import com.med.disease.tracking.app.model.response.JwtResponse;
import com.med.disease.tracking.app.repository.IUserRepository;
import com.med.disease.tracking.app.service.RegisterEmployeeService;
import com.med.disease.tracking.app.service.impl.UserDetailsImpl;
import com.med.disease.tracking.app.util.otp.OTPUtil;

@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	RegisterEmployeeService registerEmployeeService;

	@Autowired
	BeanFactory beanFactory;

	@Autowired
	JwtUtils jwtUtils;
	
	
	@GetMapping("/")
	public String main() {
		return "static page display - welcome";
	}
	
	@GetMapping("/admin/user")
	public ResponseEntity<?> user(@RequestBody UserDTO userDTO,BindingResult bindingResult) throws BeansException, Exception {
		return (ResponseEntity<?>) beanFactory.getBean(UserInfoHandler.class)
				.handle(userDTO, bindingResult,"search");
	}

	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUSer(@RequestBody UserDTO userDTO,BindingResult bindingResult) throws BeansException, Exception {
		return (ResponseEntity<?>) beanFactory.getBean(UserInfoHandler.class)
				.handle(userDTO, bindingResult,"update");
	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		logger.info("111111111111111111");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getMobile(), loginRequest.getPassword()));

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
	
	
	@PostMapping("/login")
	public ResponseEntity<?> generateOpt(@Valid @RequestBody LoginRequest loginRequest) {
	
		logger.info("INSIDE GENERATE OTP METHOD");
		String resultString ="OTP is sent to given mobile number, Please enter Otp to proceed further";
		
		logger.info("Verifying moobile with our repository..... please wait...");
		
		if(!registerEmployeeService.verifyMobile(loginRequest.getMobile())) {
			return ResponseEntity.ok("Not able to send OPT as mobile number is not matching with our records. Please enter valid mobile number !");
		}
//		String otpCode = "";
		String otpCode = OTPUtil.sendOtp(loginRequest.getMobile());
		logger.info("GENERATED OTO is -----> "+otpCode);
		
		if(!"".equals(otpCode) && !otpCode.equals("0")){
			//save otp to DB
			int res = registerEmployeeService.updateUserOTP(loginRequest.getMobile(), otpCode);
			
		}else {
			resultString = "Not able to generate OTP right now, Please try after some time.";
		}
		
		return ResponseEntity.ok("OTP is sent to given mobile number, Please enter Otp to proceed further. OTP is --"+otpCode);
	}
	
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(path = "/reguser/createUser", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO,BindingResult bindingResult) throws Exception {
		
		return (ResponseEntity<?>) beanFactory.getBean(UserRegistrationHandler.class)
				.handle(userDTO, bindingResult);
		
	}
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(path = "/reguser/uploadFile", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> uploadData(@RequestBody MultipartFile file) throws Exception {

		if (file == null) {
			String test = "Data Upload Fail. Please select Data file to upload data..";
			return new ResponseEntity<Object>(test, HttpStatus.OK);
		}

		InputStream inputStream = file.getInputStream();
		String originalName = file.getOriginalFilename();
		String name = file.getName();
		String contentType = file.getContentType();
		long size = file.getSize();

		logger.info("inputStream: " + inputStream);
		logger.info("originalName: " + originalName);
		logger.info("name: " + name);
		logger.info("contentType: " + contentType);
		logger.info("size: " + size);

		if (size == 0 && originalName.equals("")) {
			String test = "Data Upload Fail. Please select Data file to upload data..";
			return new ResponseEntity<Object>(test, HttpStatus.OK);
		}

		return (ResponseEntity<?>) beanFactory.getBean(UserRegistrationHandler.class).processCvsRequest(inputStream,
				null);
	}
	
	
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getUser(@RequestBody  UserDTO userDTO,BindingResult bindingResult) throws Exception{
		return (ResponseEntity<?>) beanFactory.getBean(UserInfoHandler.class)
				.handle(userDTO, bindingResult,"userIDSearch");
	}
}
