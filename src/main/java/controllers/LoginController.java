package controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import services.UsersService;
import entities.User;

@Controller
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private UsersService userService;
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/saveUserOnSession")
	public ModelAndView saveUserOnSession(@AuthenticationPrincipal Principal principal, HttpSession session, ModelAndView modelAndView){
		logger.info("Inside saveUserOnSession method");
		
		modelAndView = new ModelAndView(new RedirectView(""));
		User user = null;
		String username = null;
		
		try{
			username = principal.getName();
			user = userService.getUserByUsername(username);
			session.setAttribute("logged_user", user);
			
		} catch(Exception e){
			logger.error("in saveUserOnSession method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
		}
		
		return modelAndView;
		
	}
	
	@RequestMapping(value = "testUserOnSession", produces = "application/json")
	@ResponseBody
	public User testUserOnSession(HttpSession session){
		logger.info("Inside testUserOnSession method");
		User user = null;
		
		user = (User) session.getAttribute("logged_user");
		return user;
	}
}
