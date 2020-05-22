package com.med.disease.tracking.app.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.med.disease.tracking.app.dto.SurveyDTO;
import com.med.disease.tracking.app.dto.SurveyReportDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.dto.request.SurveyRequestDTO;
import com.med.disease.tracking.app.service.FeedbackService;
import com.med.disease.tracking.app.service.SurveyService;
import com.med.disease.tracking.app.service.impl.UserDetailsImpl;

@Controller
public class UserAdminScreenMVCController {

	@Autowired
	SurveyService surveyService;
	
	@Autowired 
	FeedbackService feedbackService;
	
	@RequestMapping(value="/mvc/showList", method = RequestMethod.POST)
    public String showAddUserPage(ModelMap model){
        return "addUser";
    }
	
	
	@RequestMapping(value="/mvc/login", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        return "login";
    }
	
	@RequestMapping(value="/mvc/home", method=RequestMethod.GET)
	public String homePage(ModelMap model) {
		return "home";
	}
	
	
	@RequestMapping(value="/mvc/search", method=RequestMethod.POST)
	public String search(ModelMap model) {
		return "search";
	}
	
	
	@RequestMapping(value="/mvc/userSearch", method=RequestMethod.POST)
	public String userSearch(ModelMap model) {
		return "editUser";
	}
	
	@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('MANAGER') ")
	@RequestMapping(value="/mvc/feedBack", method=RequestMethod.POST)
	public String feedbackReport(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		SurveyReportDTO surveyReportDTO = null;
		Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		GrantedAuthority ga = new SimpleGrantedAuthority("MANAGER");
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(ga)){
			System.err.println("11111111111111111111111111111");
			
		}else {
			System.err.println("22222222222");
			UserDetailsImpl ud = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ud.getUserId();
//			response.getHeader("Authorization");
			List<SurveyDTO> surveyList = (List<SurveyDTO>) surveyService.getSurveys(new SurveyRequestDTO());
			
			FetchFeedbackRequestDTO fetchFeedbackRequestDTO = new FetchFeedbackRequestDTO();
			fetchFeedbackRequestDTO.setSurveyId(surveyList.get(0).getSurveyId());
			surveyReportDTO = feedbackService.fetchAllSurveyFeedbacks(fetchFeedbackRequestDTO);
			
		}
//		response.addHeader("Authorization", response.`);
		model.addAttribute(surveyReportDTO);
		return "userFeedbackReport";
	}
	
}
