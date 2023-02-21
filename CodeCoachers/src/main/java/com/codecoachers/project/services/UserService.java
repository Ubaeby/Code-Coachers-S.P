package com.codecoachers.project.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;


import com.codecoachers.project.models.LoginUser;
import com.codecoachers.project.models.User;
import com.codecoachers.project.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userR;
	
	public User register(User newUser, BindingResult result) {
		Optional<User> possibleUser = userR.findByEmail(newUser.getEmail());
		if (possibleUser.isPresent()) {
			result.rejectValue("email", "Matches", "Email has been taken");
		}
		if (result.hasErrors()) {
			return null;
		}
		
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		return userR.save(newUser);
	}
	
	public User login(LoginUser newLogin, BindingResult result) {
		Optional<User> possibleUser = userR.findByEmail(newLogin.getEmail());
		
		if (!possibleUser.isPresent()) {
			result.rejectValue("email", "Matches", "Email not found");
			return null;
		}
		
		User nUser = possibleUser.get();
		if (!BCrypt.checkpw(newLogin.getPassword(), nUser.getPassword())) {
			result.rejectValue("password", "Matches", "Incorrect Password");
		}
		if (result.hasErrors()) {
			return null;
		}
		return nUser;
	}
	
	public List<User> allUsers() {
		return userR.findAll();
	}
	
	public User updateUser(User u) {
		return userR.save(u);
	}
	
	public User findUserId(Long id) {
		Optional<User> option = userR.findById(id);
		if(option.isPresent()) {
			return option.get();
		}
		return null;
	}
	
	//create list for reviews and coaches
	
}
