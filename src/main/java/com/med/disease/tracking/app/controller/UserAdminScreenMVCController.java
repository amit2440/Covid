package com.med.disease.tracking.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserAdminScreenMVCController {

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
	
}
