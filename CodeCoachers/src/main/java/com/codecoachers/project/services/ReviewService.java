package com.codecoachers.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codecoachers.project.models.Coach;
import com.codecoachers.project.models.Review;
import com.codecoachers.project.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository review;
	
	public List<Review> allReview() {
		return review.findAll();
	}
	
	public Review createReview(Review r) {
		return review.save(r);
	}
	
	public Review updateReview(Review r) {
		return review.save(r);
	}
	
	public Review findReview(Long id) {
		Optional<Review> option = review.findById(id);
		if(option.isPresent()) {
			return option.get();
		}
		return null;
	}
	
	public List<Review> reviewsInCoach(Long id) {
		return review.findByCoachIdIs(id);
	}
	
	public void deleteReview(Review r) {
		review.delete(r);
	}
	
	public void addToCoach(Review r, Coach c) {
		r.setCoach(c);
		review.save(r);
	}
	
	public void removeCoach(Review r) {
		review.delete(r);
		r.setCoach(null);
	}
}
