package com.med.disease.tracking.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserAdminScreenMVCController {

	@RequestMapping(value="/mvc/showList", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        return "userFBReport";
    }
	
}
