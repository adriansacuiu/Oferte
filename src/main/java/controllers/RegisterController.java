package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import entities.Department;
import entities.User;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import services.DepartmentService;
import services.UsersService;
import util.ImageUploadException;
import static util.OperationsUtils.*;

@PreAuthorize("isAnonymous()")
@RequestMapping(value = "register")
@Controller
public class RegisterController {
	private static final Logger logger = Logger.getLogger(RegisterController.class);

	@Autowired
	private UsersService userService;
	
	@Autowired
	private DepartmentService departmentService;

	@PreAuthorize("isAnonymous()")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView createUser() {
		logger.info("Inside createUser method");

		ModelAndView mv = new ModelAndView("views/register.jsp");
		try{
			List<Department> departments = departmentService.getAllDepartments();
			mv.addObject("user", new User());
			mv.addObject("departments", departments);
			
		} catch(Exception e){
			logger.error("in createUser method Exception: " + e.getMessage());
		}
		return mv;
	}

	@PreAuthorize("isAnonymous()")
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute("user") User user, HttpServletRequest request, @RequestParam(value = "image", required = false) MultipartFile image) {
		logger.info("Inside registerUser method");
		ModelAndView modelAndView = new ModelAndView("index.jsp");
		Long idDepartment = 0L;
		Department department = null;

		try {
			idDepartment = Long.parseLong(request.getParameter("idDepartment"));
			String username = user.getUsername();
			department = departmentService.getDepartmentById(idDepartment);
			user.setDepartment(department);

			if (image != null && !image.isEmpty()) {
				validateImage(image);
				saveImage(username, image);
			}

			String password = user.getPassword();
			userService.addUser(user);
			
			user.setPassword(password);
			modelAndView.addObject("registered_user", user);

		} catch (ImageUploadException iue) {
			logger.error("in registerUser method ImageUploadException: " + iue.getMessage());
			iue.printStackTrace();
			//modelAndView.setViewName("register");
			modelAndView.addObject("error", iue.getMessage());

		} 
		
		catch (Exception e) {
			logger.error("in registerUser method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			//modelAndView.setViewName("register");
			modelAndView.addObject("error", "Error registering new user!");
		}

		return modelAndView;
	}
	
}
