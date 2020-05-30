package com.med.disease.tracking.app.controller;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.med.disease.tracking.app.dto.SurveyDTO;
import com.med.disease.tracking.app.dto.SurveyFeedbackDTO;
import com.med.disease.tracking.app.dto.SurveyReportDTO;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.dto.request.SurveyRequestDTO;
import com.med.disease.tracking.app.handler.UserRegistrationHandler;
import com.med.disease.tracking.app.service.FeedbackService;
import com.med.disease.tracking.app.service.SurveyService;
import com.med.disease.tracking.app.service.impl.UserDetailsImpl;

@Controller
public class UserAdminScreenMVCController {

	private static final Logger logger = LoggerFactory.getLogger(UserAdminScreenMVCController.class);
	
	@Autowired
	SurveyService surveyService;

	@Autowired
	FeedbackService feedbackService;
	

	@Autowired
	BeanFactory beanFactory;

	@RequestMapping(value = "/mvc/showList", method = RequestMethod.POST)
	public String showAddUserPage(ModelMap model) {
		return "addUser";
	}

	@RequestMapping(value = "/mvc/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/mvc/home", method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		return "home";
	}

	@RequestMapping(value = "/mvc/search", method = RequestMethod.POST)
	public String search(ModelMap model) {
		return "search";
	}

	@RequestMapping(value = "/mvc/userSearch", method = RequestMethod.POST)
	public String userSearch(ModelMap model) {
		return "editUser";
	}

	@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('MANAGER') ")
	@RequestMapping(value = "/mvc/feedBack", method = RequestMethod.POST)
	public String feedbackReport(ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String logged_in = "";
		SurveyReportDTO surveyReportDTO = null;
		SurveyFeedbackDTO surveyFeedbackDTO = null;
		UserDetailsImpl ud = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		GrantedAuthority ga = new SimpleGrantedAuthority("MANAGER");
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(ga)) {
			System.err.println("11111111111111111111111111111");
			logged_in = "MANAGER";
			
			List<SurveyDTO> surveyList = (List<SurveyDTO>) surveyService.getSurveys(new SurveyRequestDTO());
			FetchFeedbackRequestDTO fetchFeedbackRequestDTO = new FetchFeedbackRequestDTO();
			fetchFeedbackRequestDTO.setSurveyId(surveyList.get(0).getSurveyId());
			fetchFeedbackRequestDTO.setUserId(ud.getUserId());
			surveyFeedbackDTO = feedbackService.fetchSurveyFeedbacks(fetchFeedbackRequestDTO);
			model.addAttribute("surveyReportDTO", surveyFeedbackDTO);

		} else {
			List<SurveyDTO> surveyList = (List<SurveyDTO>) surveyService.getSurveys(new SurveyRequestDTO());
			FetchFeedbackRequestDTO fetchFeedbackRequestDTO = new FetchFeedbackRequestDTO();
			fetchFeedbackRequestDTO.setSurveyId(surveyList.get(0).getSurveyId());
			surveyReportDTO = feedbackService.fetchAllSurveyFeedbacks(fetchFeedbackRequestDTO);
			logged_in = "ADMIN";
			surveyReportDTO.setAdmin(new UserDTO());
			surveyReportDTO.getAdmin().setFirstName(ud.getFirstName());
			surveyReportDTO.getAdmin().setLastName(ud.getLastName());
			model.addAttribute("surveyReportDTO", surveyReportDTO);
		}
		model.addAttribute("logged_in", logged_in);
		model.addAttribute("fetchFeedbackRequestDTO", new FetchFeedbackRequestDTO());
		return "userFeedbackReport";
	}

	
	@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('MANAGER') ")
	@RequestMapping(value = "/mvc/feedBackRes", method = RequestMethod.POST)
	public String feedBackRes(@ModelAttribute("fetchFeedbackRequestDTO") FetchFeedbackRequestDTO fetchFeedbackRequestDTO)
			throws Exception {
		System.out.println("2222222222222222222222 ----> "+fetchFeedbackRequestDTO.getUserId());
		return "feedBackResponse";
		
	}
	
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/mvc/bulkUpload", method = RequestMethod.POST)
	public String bulkUpload(ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return "bulkUpload";
		
	}
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(path = "/mvc/uploadFile", method = RequestMethod.POST, produces = {
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
	
	@RequestMapping(value = "/mvc/testjsp", method = RequestMethod.POST)
	public String testjsp(ModelMap model) {
		return "testjspFile";
	}
	
}
