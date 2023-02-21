package com.codecoachers.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codecoachers.project.models.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>{

	List<Review> findAll();
	List<Review> findByCoachIdIs(Long id);
}
