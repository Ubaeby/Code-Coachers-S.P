package com.codecoachers.project.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codecoachers.project.models.LoginUser;
import com.codecoachers.project.models.User;
import com.codecoachers.project.services.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService uServ;
		
	@GetMapping("/")
	public String registerPage(Model model) {
		model.addAttribute("newUser", new User());
		return "register.jsp";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("newLogin", new LoginUser());
		return "login.jsp";
	}

	@PostMapping("/")
	public String register(
			@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "register.jsp";
		}
		User theUser = uServ.register(newUser, result);
		session.setAttribute("userId", theUser.getId());
		return "redirect:/home";
	}
	
	@PostMapping("/login")
	public String login(
			@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
		User theUser = uServ.login(newLogin, result);
		
		if (result.hasErrors() || theUser == null) {
			model.addAttribute("newUser", new User());
			return "login.jsp";
		}
		
		session.setAttribute("userId", theUser.getId());
		return "redirect:/home";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("coachId");
		return "redirect:/login";
	}
}
