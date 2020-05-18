package com.med.disease.tracking.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.med.disease.tracking.app.dto.ManagerDTO;
import com.med.disease.tracking.app.handler.ManagerHandler;

@RestController
public class ManagerController {

	@Autowired
	BeanFactory beanFactory;
	
	
	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(path = "/reg/createMgr", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	
	public ResponseEntity<?> createUser(@RequestBody ManagerDTO managerDTO,BindingResult bindingResult) throws Exception {
		
		return (ResponseEntity<?>) beanFactory.getBean(ManagerHandler.class)
				.handle(managerDTO, bindingResult);
		
	} 
	
	
	//MANAGER UPDATE IS Pending, MAnager search list is pending and TESTING IS PENDING
//	Also once manager is added into system need to add manage id into user object that is also pending.
//	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(path = "/reg/managers", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> listManagers() throws BeansException, Exception{
		return (ResponseEntity<?>) beanFactory.getBean(ManagerHandler.class).getManagerList();
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(path = "/reg/updMgr", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> updateUsrManager(@RequestBody ManagerDTO managerDTO,BindingResult bindingResult) throws BeansException, Exception{
		
		return (ResponseEntity<?>) beanFactory.getBean(ManagerHandler.class).handleUpdMgrReq(managerDTO, bindingResult);
	}
}
