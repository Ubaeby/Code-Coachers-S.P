package com.codecoachers.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codecoachers.project.models.Coach;

@Repository
public interface CoachRepository extends CrudRepository<Coach, Long> {

		List<Coach> findAll();
		Coach findByIdIs(Long id);

}
