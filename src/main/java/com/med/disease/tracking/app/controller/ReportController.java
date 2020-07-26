package com.med.disease.tracking.app.controller;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.med.disease.tracking.app.dto.EPassRequestDTO;
import com.med.disease.tracking.app.dto.ReportRequestDTO;
import com.med.disease.tracking.app.handler.EpassReportHandler;
import com.med.disease.tracking.app.handler.ReportHandler;

@Controller
public class ReportController {


	@Autowired
	BeanFactory beanFactory;
	
	@GetMapping(path = "/reports/managerRpt", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchSurvey(@ModelAttribute ReportRequestDTO requestDTO, BindingResult bindingResult)
			throws Exception {
		return (ResponseEntity<?>) beanFactory.getBean(ReportHandler.class).handle(requestDTO, bindingResult);
	}
	
	
//	@GetMapping(path = "/reports/userEpassRpt", produces = MediaType.APPLICATION_JSON_VALUE)
	
	
	
	
}
