//package com.med.disease.tracking.app.controller;
//
//import java.io.InputStream;
//
//import org.jboss.logging.Logger;
//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.med.disease.tracking.app.handler.UserRegistrationHandler;
//import com.med.disease.tracking.app.service.impl.RegisterEmployeeServiceImpl;
//
//@RestController
//public class RegisterEmployeeController {
//
//	private static final Logger logger = Logger.getLogger(RegisterEmployeeController.class);
//
//	@Autowired
//	RegisterEmployeeServiceImpl registerEmployeeServiceImpl;
//
//	@Autowired
//	BeanFactory beanFactory;
//
//	@RequestMapping(path = "/reguser/uploadFile", method = RequestMethod.POST, produces = {
//			MediaType.APPLICATION_JSON_VALUE })
//	public ResponseEntity<?> uploadData(@RequestBody MultipartFile file) throws Exception {
//
//		if (file == null) {
//			String test = "Data Upload Fail. Please select Data file to upload data..";
//			return new ResponseEntity<Object>(test, HttpStatus.OK);
//		}
//
//		InputStream inputStream = file.getInputStream();
//		String originalName = file.getOriginalFilename();
//		String name = file.getName();
//		String contentType = file.getContentType();
//		long size = file.getSize();
//
//		logger.info("inputStream: " + inputStream);
//		logger.info("originalName: " + originalName);
//		logger.info("name: " + name);
//		logger.info("contentType: " + contentType);
//		logger.info("size: " + size);
//
//		if (size == 0 && originalName.equals("")) {
//			String test = "Data Upload Fail. Please select Data file to upload data..";
//			return new ResponseEntity<Object>(test, HttpStatus.OK);
//		}
//
//		return (ResponseEntity<?>) beanFactory.getBean(UserRegistrationHandler.class).processCvsRequest(inputStream,
//				null);
//	}
//
//	@GetMapping("/reguser/getEx")
//	public ResponseEntity<String> getEx() {
//		return new ResponseEntity<String>("Hemmmmmmm", HttpStatus.OK);
//	}
//}
