package controllers;

import static util.OperationsUtils.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import entities.Department;
import entities.User;
import services.DepartmentService;
import services.UsersService;
import util.ImageUploadException;

@PreAuthorize("isAuthenticated()")
@Controller
public class MyProfileController {
	private static final Logger logger = Logger.getLogger(MyProfileController.class);

	@Autowired
	private UsersService userService;

	@Autowired
	private DepartmentService departmentService;

	@PreAuthorize("isAuthenticated() and #username == principal.username")
	@RequestMapping(value = "{username}/createUserObject", method = RequestMethod.GET)
	public ModelAndView createUserObject(@PathVariable String username) {
		logger.info("Inside createUserObject method");

		ModelAndView mv = new ModelAndView("../views/editProfile.jsp");
		try {
			List<Department> departments = departmentService.getAllDepartments();
			mv.addObject("user", new User());
			mv.addObject("departments", departments);

		} catch (Exception e) {
			logger.error("in createUserObject method Exception: " + e.getMessage());
		}
		return mv;
	}

	@PreAuthorize("isAuthenticated() and #username == principal.username")
	@RequestMapping(value = "{username}/updateUser", method = RequestMethod.POST)
	public ModelAndView updateUser(@PathVariable String username, @ModelAttribute("user") User user, HttpServletRequest request,
					HttpServletResponse response, @RequestParam(value = "image", required = false) MultipartFile image) {
		logger.info("Inside updateUser method");
		response.setHeader("Cache-Control", "no-cache");
		ModelAndView modelAndView = new ModelAndView(new RedirectView("../"));
		HttpSession session = request.getSession();
		Department department = null;
		String idDepartmentString = null;
		Long idDepartment = 0L;
		
		try {
			if (image != null && !image.isEmpty()) {
				validateImage(image);
				deleteDir(username);
				saveImage(user.getUsername(), image);
			}

			idDepartmentString = request.getParameter("idDepartment");
			idDepartment = (idDepartmentString == null)? 0L : Long.parseLong(request.getParameter("idDepartment"));
			department = departmentService.getDepartmentById(idDepartment);
			user.setDepartment(department);

			userService.updateUser(user);
			session.setAttribute("logged_user", user);

			String role = user.getRole().toUpperCase();
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
			Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (ImageUploadException iue) {
			logger.error("in updateUser method ImageUploadException: " + iue.getMessage());
			iue.printStackTrace();
			// modelAndView.setViewName("#register");
			modelAndView.addObject("error", iue.getMessage());

		}

		catch (Exception e) {
			logger.error("in updateUser method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			// modelAndView.setViewName("#register");
			modelAndView.addObject("error", "Error updating new user!");
		}

		return modelAndView;
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "testPrincipal", produces = "application/json")
	@ResponseBody
	public Principal testPrincipal(@AuthenticationPrincipal Principal principal) {
		logger.info("Inside testPrincipal method");
		return principal;
	}

}