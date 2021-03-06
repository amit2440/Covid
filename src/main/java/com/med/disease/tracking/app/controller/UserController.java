package com.med.disease.tracking.app.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.med.disease.tracking.app.config.jwt.JwtUtils;
import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EPassRequestDTO;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.exception.CovidAppException;
import com.med.disease.tracking.app.exception.ErrorResponse;
import com.med.disease.tracking.app.handler.EpassReportHandler;
import com.med.disease.tracking.app.handler.UserInfoHandler;
import com.med.disease.tracking.app.handler.UserRegistrationHandler;
import com.med.disease.tracking.app.model.request.LoginRequest;
import com.med.disease.tracking.app.model.response.JwtResponse;
import com.med.disease.tracking.app.repository.IUserRepository;
import com.med.disease.tracking.app.service.RegisterEmployeeService;
import com.med.disease.tracking.app.service.impl.UserDetailsImpl;
import com.med.disease.tracking.app.util.ErrorUtil;
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
	
	
	@Value("${covid.app.authkey}")
	private  String AUTHKEY;
	
	@Value("${covid.app.templateId}")
	private String TEMPLATE_ID;
	
	@Value("${covid.app.otpLength}")
	private String OTP_LENGHT;
	
	@Value("${covid.app.enableOTP}")
	private String enableOtp;
	
	@Value("${covid.app.defaultOTP}")
	private String defaultOTP;
	
	@Value("${covid.app.defaultNumber}")
	private String defaultNumber;
	
	@GetMapping("/")
	public String main() {
		return "static page display - welcome";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/admin/user")
	public ResponseEntity<?> user(@RequestBody(required = false) UserDTO userDTO,BindingResult bindingResult) throws BeansException, Exception {
		return (ResponseEntity<?>) beanFactory.getBean(UserInfoHandler.class)
				.handle(userDTO, bindingResult,"search");
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/admin/updateUser")
	public ResponseEntity<?> updateUSer(@RequestBody UserDTO userDTO,BindingResult bindingResult) throws BeansException, Exception {
		return (ResponseEntity<?>) beanFactory.getBean(UserInfoHandler.class)
				.handle(userDTO, bindingResult,"update");
	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,BindingResult bindingResult) {
		logger.info("111111111111111111");
		
		BindingResult error  = registerEmployeeService.verifyMobile(loginRequest.getMobile(),bindingResult);
		if(error.hasErrors()) {
			ErrorUtil.processError(error, Constant.Module.REGISTER_USER);
		}
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getMobile(), loginRequest.getPassword()));

		if(!authentication.isAuthenticated()) {
			error.rejectValue(Constant.Field.MOBILE, "authentication.failed", new Object[] { Constant.Field.MOBILE }, null);
			ErrorUtil.processError(error, Constant.Module.REGISTER_USER);
		}
		
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUserId(),
												 userDetails.getMobile(), 
												 roles));
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> generateOpt(@Valid @RequestBody LoginRequest loginRequest,BindingResult bindingResult) {
	
		logger.info("INSIDE GENERATE OTP METHOD");
		String resultString ="OTP is sent to given mobile number, Please enter Otp to proceed further";
		
		logger.info("Verifying moobile with our repository..... please wait...");
		BindingResult error  = registerEmployeeService.verifyMobile(loginRequest.getMobile(),bindingResult);
		if(error.hasErrors()) {
			ErrorUtil.processError(error, Constant.Module.REGISTER_USER);
		}
//		if(!registerEmployeeService.verifyMobile(loginRequest.getMobile(),bindingResult)) {
//			return ResponseEntity.ok("Not able to send OPT as mobile number is not matching with our records. Please enter valid mobile number !");
//		}
		List defaultNumberList = new ArrayList();
		if(!"".equalsIgnoreCase(defaultNumber) && !defaultNumber.isEmpty()) {
			defaultNumberList = Arrays.asList(defaultNumber.split(","));
		}
		 
		
		String otpCode = "";
		if(!"".equalsIgnoreCase(enableOtp) && enableOtp.equalsIgnoreCase("Y")) {
			if(defaultNumberList.contains(loginRequest.getMobile())) {
				if(!"".equalsIgnoreCase(defaultOTP) && !defaultOTP.isEmpty()) {
					otpCode = defaultOTP;
				}else {
					otpCode="1234";
				}
			}else {
				otpCode = OTPUtil.sendOtp(loginRequest.getMobile(),AUTHKEY,TEMPLATE_ID,OTP_LENGHT);
			}
			
		}else {
			if(!"".equalsIgnoreCase(defaultOTP) && !defaultOTP.isEmpty()) {
				otpCode = defaultOTP;
			}else {
				otpCode="1234";
			}
//			otpCode = OTPUtil.sendOtp(loginRequest.getMobile());
		}
		logger.info("GENERATED OTO is -----> "+otpCode);
		
		if(!"".equals(otpCode) && !otpCode.equals("0")){
			//save otp to DB
			int res = registerEmployeeService.updateUserOTP(loginRequest.getMobile(), otpCode);
			
		}else {
			resultString = "Not able to generate OTP right now, Please try after some time.";
			ErrorResponse errorRes = new ErrorResponse();
			errorRes.setTitle("Mobile Number or OTP is not valid");
			errorRes.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
			errorRes.setHttpStatusValue(HttpStatus.UNPROCESSABLE_ENTITY);
//			return new ResponseEntity<ErrorResponse>(errorRes, HttpStatus.UNPROCESSABLE_ENTITY);
			
			throw new CovidAppException(errorRes);
		}
		
		return ResponseEntity.ok("OTP is sent to given mobile number, Please enter Otp to proceed further.");
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
	
	
	   
	@GetMapping("/admin/user/{userId}")
	public ResponseEntity<?> getUser(@RequestBody  UserDTO userDTO,BindingResult bindingResult) throws Exception{
		return (ResponseEntity<?>) beanFactory.getBean(UserInfoHandler.class)
				.handle(userDTO, bindingResult,"userIDSearch");
	}
	
	@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('MANAGER') ")
	@PostMapping(path = "/admin/userEpassRpt", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> allowedEpassReport(@RequestBody EPassRequestDTO requestDTO, BindingResult bindingResult)
			throws Exception {
		UserDetailsImpl ud = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		GrantedAuthority ga = new SimpleGrantedAuthority("MANAGER");
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(ga)) {
			requestDTO.setMgrId(ud.getUserId());
		}
		return (ResponseEntity<?>) beanFactory.getBean(EpassReportHandler.class).handle(requestDTO, bindingResult);
	}
}
