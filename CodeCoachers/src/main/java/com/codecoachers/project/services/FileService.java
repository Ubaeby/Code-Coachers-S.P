package com.codecoachers.project.services;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.codecoachers.project.models.Coach;
import com.codecoachers.project.models.FileInfo;
import com.codecoachers.project.repositories.FileRepository;

@Service
public class FileService {

	@Autowired
	private FileRepository fileR;
	
	public FileInfo store(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileInfo fileinfo = new FileInfo(fileName, file.getContentType(), file.getBytes());
		
		return fileR.save(fileinfo);
	}
	
	public FileInfo getFile(String id) {
		return fileR.findById(id).get();
	}
	
	public Stream<FileInfo> getAllFiles() {
		return fileR.findAll().stream();
	}
	
	public void addToCoach(FileInfo f, Coach c) {
		f.setCoach(c);
		fileR.save(f);
	}
}
