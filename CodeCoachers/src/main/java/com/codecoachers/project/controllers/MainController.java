package com.codecoachers.project.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;

import com.codecoachers.project.models.Coach;
import com.codecoachers.project.models.Review;
import com.codecoachers.project.models.User;
import com.codecoachers.project.services.CoachService;
import com.codecoachers.project.services.ReviewService;
import com.codecoachers.project.services.UserService;

@Controller
public class MainController {

	@Autowired
	private UserService uServ;
	@Autowired
	private CoachService cServ;
	@Autowired
	private ReviewService rServ;
	
	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long randomId = (long) (Math.random() * 2);
		Math.floor(randomId);
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("allUsers", uServ.findUserId(userId));
		model.addAttribute("coaches", cServ.allCoach());
		model.addAttribute("number", randomId);
		return "home.jsp";
	}
	
	@GetMapping("/profile/new")
	public String coachPage(
			@ModelAttribute("newCoach") Coach newCoach, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/logout";
		} 
		User u = uServ.findUserId(userId);
		if(u.getCoach() != null) {	
			return "redirect:/home";
		} 
		model.addAttribute("users", u);
		return "newCoach.jsp";
	}
	
	
	@PostMapping("/profile/new")
	public String addCoach(
			@Valid @ModelAttribute("newCoach") Coach newCoach, BindingResult result, HttpSession session
/*			@RequestParam("image") MultipartFile file */) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}
		if(result.hasErrors()) {
			return "newCoach.jsp";
		}
		else {
			User user = uServ.findUserId(userId);
			Coach addC = new Coach(newCoach.getPrice(), newCoach.getBio(), newCoach.getColor(), newCoach.getLang());
			addC.setUser(user);
			cServ.addCoach(addC);
			uServ.updateUser(user);
			return "redirect:/home";
		}
	}
	
	@GetMapping("/profile/{id}")
	public String showProfile(
			@PathVariable("id") Long id, HttpSession session, Model model,
			@ModelAttribute("review") Review newReview) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long randomId = (long) (Math.random() * 2);
		Math.floor(randomId);
		model.addAttribute("number", randomId);
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("allUsers", uServ.findUserId(userId));
		Coach c = cServ.findCoachId(id);
		model.addAttribute("coaches", c);
		model.addAttribute("reviews", rServ.allReview());
		return "profile.jsp";
	}
	
	@PostMapping("/profile/{id}")
	public String createReview(
			@PathVariable("id") Long id, HttpSession session, Model model,
			@Valid @ModelAttribute("review") Review newReview, BindingResult result) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Long userId = (Long) session.getAttribute("userId");
		Coach coach = cServ.findCoachId(id);
		
		if (result.hasErrors()) {
			model.addAttribute("coaches", coach);
			model.addAttribute("reviews", rServ.reviewsInCoach(id));
			Long randomId = (long) (Math.random() * 2);
			Math.floor(randomId);
			model.addAttribute("number", randomId);
			return "redirect:/profile/" + id;
		}
		Review theReview = new Review(newReview.getMessage());
		theReview.setCoach(coach);
		theReview.setReviewer(uServ.findUserId(userId));
		rServ.createReview(theReview);
		
		return "redirect:/profile/" + id;
	}
	
	
	@GetMapping("/profile/edit/{id}")
	public String editPage(
			@PathVariable("id") Long id, HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Coach c = cServ.findCoachId(id);
		model.addAttribute("coaches", c);
		return "editProfile.jsp";
	}
	
	@PutMapping("/profile/edit/{id}")
	public String editProfile(
			@PathVariable("id") Long id,
			@Valid @ModelAttribute("newCoach") Coach newCoach, BindingResult result, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Long userId = (Long) session.getAttribute("userId");
		User user = uServ.findUserId(userId);
		
		if (result.hasErrors()) {
			return "editProfile.jsp";
		}
		else {
			Coach coach = cServ.findCoachId(id);
			newCoach.setUser(coach.getUser());
			newCoach.setUser(user);
			cServ.updateCoach(newCoach);
			return "redirect:/profile/{id}";
		}
	}
	
	@RequestMapping("/profile/delete/{id}")
	public String deleteCoach( 
			@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}
		
		Coach coach = cServ.findCoachId(id);
		cServ.deleteCoach(coach);

		return "redirect:/home";
	}
	
}
