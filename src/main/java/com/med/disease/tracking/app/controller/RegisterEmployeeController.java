package com.med.disease.tracking.app.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.handler.UserRegistrationHandler;
import com.med.disease.tracking.app.model.User;
import com.med.disease.tracking.app.service.impl.RegisterEmployeeServiceImpl;

@RestController
public class RegisterEmployeeController {

	private static final Logger logger = Logger.getLogger(RegisterEmployeeController.class);
	@Autowired
	RegisterEmployeeServiceImpl registerEmployeeServiceImpl;
	
	@Autowired
	BeanFactory beanFactory;
	
	

	@RequestMapping(path = "/reguser/uploadFile", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<?> uploadData(@RequestParam("file") MultipartFile file,BindingResult bindingResult) throws Exception {
		
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
		
		return (ResponseEntity<?>) beanFactory.getBean(UserRegistrationHandler.class)
				.processCvsRequest(inputStream, bindingResult);
		
	}
	
	
	
	

////	@RequestMapping(path = "/reguser/uploadFile", method = RequestMethod.POST, produces = {
////			MediaType.APPLICATION_JSON_VALUE })
////	public ResponseEntity<?> uploadData(@RequestParam("file") MultipartFile file) throws Exception {
////
////		List<String> failUploadList = null;
////		System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
////		if (file == null) {
////			String test = "Data Upload Fail. Please select Data file to upload data..";
////			return new ResponseEntity<Object>(test, HttpStatus.OK);
////		}
////
////		InputStream inputStream = file.getInputStream();
////		String originalName = file.getOriginalFilename();
////		String name = file.getName();
////		String contentType = file.getContentType();
////		long size = file.getSize();
////
////		logger.info("inputStream: " + inputStream);
////		logger.info("originalName: " + originalName);
////		logger.info("name: " + name);
////		logger.info("contentType: " + contentType);
////		logger.info("size: " + size);
////
////		if (size == 0 && originalName.equals("")) {
////			String test = "Data Upload Fail. Please select Data file to upload data..";
////			return new ResponseEntity<Object>(test, HttpStatus.OK);
////		}
////		
////		
////		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
////			int i = 0;
////			while (reader.ready()) {
////				if (i == 0) {
////					String line = reader.readLine();
////
////					
////				} else {
////					String line = reader.readLine();
////					if(!"".equals(line)) {
//////						User user = this.getUSerObject(line);
//////						logger.info(line);
////						int res = registerEmployeeServiceImpl.registerEmpFormCSV(line);
////						if(res <= 0) {
////							if(failUploadList==null) {
////								failUploadList = new ArrayList<String> ();
////							}
////							failUploadList.add("Update failed for User detail : "+line);	
////						}
////					}
////				}
////				
////				i++;
////			}
////		} catch (FileNotFoundException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		
////		if(failUploadList!=null && failUploadList.size()>0) {
////			return new ResponseEntity<Object>(originalName, HttpStatus.OK);
////		}
////		
////		// Do processing with uploaded file data in Service layer
////		return new ResponseEntity<Object>(failUploadList, HttpStatus.OK);
////	}
////
////	
//	
//	
//	
//	@GetMapping("/reguser/getEx")
//	public ResponseEntity<String> getEx() {
//		return new ResponseEntity<String>("Hemmmmmmm", HttpStatus.OK);
//	}
//
//	protected User getUSerObject(String csvLine) {
//		
//		User  user = null;
//		if(!"".equals(csvLine) && csvLine.length()>0) {
//			String[] userArr = csvLine.split(",");
//			user = new User();
//			user.setUserName(userArr[0]!=null?userArr[0]:null);
//			user.setPassword(userArr[0]!=null?userArr[0]:null);
////			user.setActive(userArr[4].equalsIgnoreCase("Y")?true:false);
//			
//		}
//		return user;
//		
//	}
//	
//	
	
}
