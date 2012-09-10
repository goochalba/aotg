package com.aotg.mvc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aotg.mvc.domain.Users;
import com.aotg.mvc.service.LoginService;


/**
 * Handles and retrieves person request
 */
@Controller
public class LoginController {

	protected static Logger logger = Logger.getLogger("LoginController");
	//private String requestType = request.getParameter("session.telephone.ani");
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	/**
	 * Handles and retrieves all users and show it in a JSP page
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(@RequestParam("CallerID") String phoneNumber, Model model) {
    	
    	logger.debug("Received request to get users information");
    	
    	// Retrieve all users by delegating the call to LoginService
    	List<Users> users = loginService.getAll(phoneNumber);
    	
    	// Attach users to the Model
    	model.addAttribute("users", users);
    	
    	// This will resolve to /WEB-INF/jsp/login.jsp
    	return "login";
	}  
}
