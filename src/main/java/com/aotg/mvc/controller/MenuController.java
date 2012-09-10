package com.aotg.mvc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aotg.mvc.domain.Menu;
import com.aotg.mvc.service.MenuService;


/**
 * Handles and retrieves person request
 */
@Controller
public class MenuController {

	protected static Logger logger = Logger.getLogger("MenuController");
	
	@Resource(name="menuService")
	private MenuService menuService;
	
	/**
	 * Handles and retrieves all users and show it in a JSP page
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String getMenu(@RequestParam(value = "LastLogin", defaultValue = "false") String updateLogin,
                          @RequestParam("UserID") Integer userId,
                          @RequestParam("ParentMenuID") Integer parentMenuId,
                          Model model) {
    	   	    	   	  	   	
    	logger.debug("Check to see if user just logged in");
    	if (updateLogin.equals("true")) {
    	
    	  menuService.lastLogin(userId);
    	
    	} // end if
    	    	
    	// Retrieve all users by delegating the call to LoginService
    	List<Menu> menu = menuService.listMenu(parentMenuId);
    	
    	// Attach menu items to the Model
    	model.addAttribute("menu", menu);
    	model.addAttribute("UserID", userId);
    	
    	// This will resolve to /WEB-INF/jsp/menu.jsp
    	return "menu";
	}  
}