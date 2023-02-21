package com.codecoachers.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codecoachers.project.models.FileInfo;

@Repository
public interface FileRepository extends JpaRepository<FileInfo, String> {

}
