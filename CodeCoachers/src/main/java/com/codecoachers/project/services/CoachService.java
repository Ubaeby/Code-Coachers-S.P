 package com.codecoachers.project.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.codecoachers.project.models.Coach;
import com.codecoachers.project.repositories.CoachRepository;

@Service
public class CoachService {

	@Autowired
	private CoachRepository coach;
	
	public Coach addCoach(Coach c) {
		return coach.save(c);
	}
	
	public Coach updateCoach(Coach c) {
		return coach.save(c);
	}
	
	public void deleteCoach(Coach c) {
		c.getReviews().remove(0);
		coach.delete(c);
	}
	
	public List<Coach> allCoach() {
		return coach.findAll();
	}
	
	public Coach findCoachId(Long id) {
		Optional<Coach> option = coach.findById(id);
		if(option.isPresent()) {
			return option.get();
		}
		return null;
	}

	public Coach saveCoach(MultipartFile file, Double price, String bio, String color, String lang) {
		Coach c = new Coach();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("That's not a valid file");
		}
		try {
			c.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		c.setPrice(price);
		c.setBio(bio);
		c.setColor(color);
		c.setLang(lang);	
		
		return coach.save(c);
	}
}
